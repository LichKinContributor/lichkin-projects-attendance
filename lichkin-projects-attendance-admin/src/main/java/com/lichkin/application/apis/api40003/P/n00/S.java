package com.lichkin.application.apis.api40003.P.n00;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.lichkin.application.utils.LKDictUtils;
import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysEmployeeAttendanceR;
import com.lichkin.framework.db.beans.SysEmployeeR;
import com.lichkin.framework.db.enums.LikeType;
import com.lichkin.framework.defines.enums.impl.LKDateTimeTypeEnum;
import com.lichkin.framework.utils.LKDateTimeUtils;
import com.lichkin.springframework.entities.impl.SysEmployeeAttendanceEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeEntity;
import com.lichkin.springframework.services.LKApiBusGetPageService;

@Service("SysEmployeeAttendanceP00Service")
public class S extends LKApiBusGetPageService<I, O, SysEmployeeAttendanceEntity> {

	@Override
	protected void initSQL(I sin, String locale, String compId, String loginId, QuerySQL sql) {
		// 主表
		sql.select(SysEmployeeAttendanceR.id);
		sql.select(SysEmployeeAttendanceR.insertTime);
		sql.select(SysEmployeeAttendanceR.workDate);
		sql.select(SysEmployeeAttendanceR.startTime);
		sql.select(SysEmployeeAttendanceR.endTime);
		sql.select(SysEmployeeAttendanceR.dayOff);
		sql.select(SysEmployeeAttendanceR.takeWorkingDayOff);
		sql.select(SysEmployeeAttendanceR.askForLeave);
		sql.select(SysEmployeeAttendanceR.absenteeism);
		sql.select(SysEmployeeAttendanceR.beLate);
		sql.select(SysEmployeeAttendanceR.leaveEarly);
		sql.select(SysEmployeeAttendanceR.firstPunchTime);
		sql.select(SysEmployeeAttendanceR.lastPunchTime);

		// 关联表
		sql.innerJoin(SysEmployeeEntity.class, new Condition(SysEmployeeR.id, SysEmployeeAttendanceR.loginId));
		sql.select(SysEmployeeR.userName);
		sql.select(SysEmployeeR.cellphone);

		// 字典表
		int i = 0;
		LKDictUtils.usingStatus(sql, SysEmployeeAttendanceR.usingStatus, i++);

		// 筛选条件（必填项）
		// 公司ID
		addConditionCompId(false, sql, SysEmployeeAttendanceR.compId, compId, sin.getCompId());
		// 在用状态
		addConditionUsingStatus(sql, SysEmployeeAttendanceR.usingStatus, compId, sin.getUsingStatus());

		// 筛选条件（业务项）
		String userName = sin.getUserName();
		if (StringUtils.isNotBlank(userName)) {
			sql.like(SysEmployeeR.userName, LikeType.ALL, userName);
		}

		String cellphone = sin.getCellphone();
		if (StringUtils.isNotBlank(cellphone)) {
			sql.like(SysEmployeeR.cellphone, LikeType.RIGHT, cellphone);
		}

		String startDate = sin.getStartDate();
		String endDate = sin.getEndDate();
		if (StringUtils.isBlank(startDate)) {
			if (StringUtils.isBlank(endDate)) {
				sql.lte(SysEmployeeAttendanceR.workDate, LKDateTimeUtils.now(LKDateTimeTypeEnum.DATE_ONLY));
			} else {
				sql.lte(SysEmployeeAttendanceR.workDate, endDate);
			}
		} else {
			if (StringUtils.isBlank(endDate)) {
				if (LKDateTimeUtils.toDateTime(startDate).isBefore(DateTime.now())) {
					sql.gte(SysEmployeeAttendanceR.workDate, startDate);
					sql.lte(SysEmployeeAttendanceR.workDate, LKDateTimeUtils.now(LKDateTimeTypeEnum.DATE_ONLY));
				} else {
					sql.eq(SysEmployeeAttendanceR.workDate, startDate);
				}
			} else {
				sql.gte(SysEmployeeAttendanceR.workDate, startDate);
				sql.lte(SysEmployeeAttendanceR.workDate, endDate);
			}
		}

		// 排序条件
		sql.addOrders(new Order(SysEmployeeR.userName), new Order(SysEmployeeAttendanceR.workDate));
	}

}
