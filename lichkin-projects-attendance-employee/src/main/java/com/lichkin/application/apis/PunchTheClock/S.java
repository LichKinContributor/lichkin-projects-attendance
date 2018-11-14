package com.lichkin.application.apis.PunchTheClock;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.lichkin.defines.beans.LKAMapLocation;
import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysCompAttendanceAreaConfigR;
import com.lichkin.framework.db.beans.SysEmployeeAttendanceR;
import com.lichkin.framework.db.beans.eq;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.enums.impl.LKDateTimeTypeEnum;
import com.lichkin.framework.defines.enums.impl.LKMapAPIEnum;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.utils.LKAreaUtils;
import com.lichkin.framework.utils.LKBeanUtils;
import com.lichkin.framework.utils.LKDateTimeUtils;
import com.lichkin.springframework.entities.impl.SysAMapLocationEntity;
import com.lichkin.springframework.entities.impl.SysCompAttendanceAreaConfigEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeAttendanceEntity;
import com.lichkin.springframework.entities.impl.SysEmployeePunchTheClockEntity;
import com.lichkin.springframework.services.LKApiBusInsertWithoutCheckerService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service(Statics.SERVICE_NAME)
public class S extends LKApiBusInsertWithoutCheckerService<I, SysEmployeePunchTheClockEntity> {

	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		app_PunchTheClock_invalid_time(50000),

		app_PunchTheClock_invalid_area(50001),

		;

		private final Integer code;

	}


	@Override
	protected void beforeSaveMain(I sin, String locale, String compId, String loginId, SysEmployeePunchTheClockEntity entity) {
		LKAMapLocation amap = sin.getAmap();
		entity.setMapType(LKMapAPIEnum.AMAP);
		entity.setLatitude(amap.getLatitude());
		entity.setLongitude(amap.getLongitude());
		entity.setAltitude(amap.getAltitude());
		entity.setAddress(String.format("%s%s%s%s%s%s",

				StringUtils.trimToEmpty(amap.getCountry()),

				StringUtils.trimToEmpty(amap.getProvince()),

				StringUtils.trimToEmpty(amap.getCity()),

				StringUtils.trimToEmpty(amap.getDistrict()),

				StringUtils.trimToEmpty(amap.getStreet()),

				StringUtils.trimToEmpty(amap.getStreetNum())

		));
	}


	@Override
	protected void addSubs(I sin, String locale, String compId, String loginId, SysEmployeePunchTheClockEntity entity, String id) {
		LKAMapLocation amap = sin.getAmap();
		SysAMapLocationEntity aMapLocationEntity = LKBeanUtils.newInstance(true, amap, SysAMapLocationEntity.class);
		aMapLocationEntity.setBusId(id);
		aMapLocationEntity.setLocateTime(amap.getTime());
		dao.persistOne(aMapLocationEntity);
	}


	@Override
	protected void afterSubs(I sin, String locale, String compId, String loginId, SysEmployeePunchTheClockEntity entity, String id) throws LKException {
		// 查询当前打卡时间对应的考勤排班信息
		String nowTime = LKDateTimeUtils.now(LKDateTimeTypeEnum.STANDARD);
		QuerySQL sql = new QuerySQL(false, SysEmployeeAttendanceEntity.class);
		sql.eq(SysEmployeeAttendanceR.compId, compId);
		sql.eq(SysEmployeeAttendanceR.loginId, loginId);
		sql.lte(SysEmployeeAttendanceR.allowBeforeStartTime, nowTime);
		sql.gte(SysEmployeeAttendanceR.allowAfterEndTime, nowTime);
		sql.where(

				new Condition(true

						, new Condition(false, new eq(SysEmployeeAttendanceR.workDate, LKDateTimeUtils.now(LKDateTimeTypeEnum.DATE_ONLY)))

						, new Condition(false, new eq(SysEmployeeAttendanceR.workDate, LKDateTimeUtils.toString(DateTime.now().minusDays(1), LKDateTimeTypeEnum.DATE_ONLY)))

				)

		);
		sql.addOrders(new Order(SysEmployeeAttendanceR.workDate, false));
		List<SysEmployeeAttendanceEntity> list = dao.getList(sql, SysEmployeeAttendanceEntity.class);

		// 不在考勤时间范围内
		if (CollectionUtils.isEmpty(list)) {
			throw new LKException(ErrorCodes.app_PunchTheClock_invalid_time);
		}

		// 在考勤时间范围内
		// 不在考勤地区范围内
		if (!checkAttendanceArea(compId, entity.getLatitude(), entity.getLongitude(), entity.getAltitude())) {
			throw new LKException(ErrorCodes.app_PunchTheClock_invalid_area);
		}

		// 在考勤地区范围内
		SysEmployeeAttendanceEntity employeeAttendance = list.get(0);
		// 首次打卡
		if (StringUtils.isBlank(employeeAttendance.getFirstPunchTime())) {
			employeeAttendance.setFirstPunchTime(nowTime);
			// 判断是否迟到
			if (LKDateTimeUtils.toDateTime(nowTime, LKDateTimeTypeEnum.STANDARD).isAfter(LKDateTimeUtils.toDateTime(employeeAttendance.getAllowAfterStartTime(), LKDateTimeTypeEnum.STANDARD))) {
				employeeAttendance.setBeLate(true);
			}
		} else {
			employeeAttendance.setLastPunchTime(nowTime);
			// 判断是否早退
			if (LKDateTimeUtils.toDateTime(nowTime, LKDateTimeTypeEnum.STANDARD).isBefore(LKDateTimeUtils.toDateTime(employeeAttendance.getAllowBeforeEndTime(), LKDateTimeTypeEnum.STANDARD))) {
				employeeAttendance.setLeaveEarly(true);
			} else {
				employeeAttendance.setLeaveEarly(false);
			}
		}
		dao.mergeOne(employeeAttendance);
	}


	/**
	 * 验证是否在考勤地址范围内
	 * @param compId 公司ID
	 * @param latitude 纬度
	 * @param longitude 经度
	 * @param altitude 高度
	 * @return 在范围内返回true，不在范围内返回false。
	 */
	private boolean checkAttendanceArea(String compId, Double latitude, Double longitude, Double altitude) {
		QuerySQL sql = new QuerySQL(false, SysCompAttendanceAreaConfigEntity.class);
		sql.eq(SysCompAttendanceAreaConfigR.compId, compId);
		SysCompAttendanceAreaConfigEntity entity = dao.getOne(sql, SysCompAttendanceAreaConfigEntity.class);
		if (entity == null) {
			return true;
		}
		return LKAreaUtils.check(entity.getLatitude(), entity.getLongitude(), entity.getAltitude(), entity.getRadius() * 50, entity.getOgham() == null ? null : ((int) entity.getOgham()), latitude, longitude, altitude);
	}

}
