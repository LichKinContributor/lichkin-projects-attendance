package com.lichkin.application.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.framework.db.beans.DeleteSQL;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysEmployeeAttendanceR;
import com.lichkin.framework.db.beans.SysEmployeeScheduleConfigR;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.enums.impl.LKDateTimeTypeEnum;
import com.lichkin.framework.utils.LKCalendarUtils;
import com.lichkin.framework.utils.LKDateTimeUtils;
import com.lichkin.springframework.entities.impl.SysCompScheduleConfigEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeAttendanceEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeScheduleConfigEntity;
import com.lichkin.springframework.services.LKDBService;

@Service
public class SysEmployeeAttendanceService extends LKDBService {

	@Transactional
	public void resetNextWeekEmployeeAttendance(String compId, String loginId) {
		// 查询员工排班配置
		SysEmployeeScheduleConfigEntity employeeScheduleConfig = getEmployeeScheduleConfigByCompIdAndLoginId(compId, loginId);
		if (employeeScheduleConfig == null) {
			return;
		}

		// 查询公司排班配置
		SysCompScheduleConfigEntity compScheduleConfig = dao.findOneById(SysCompScheduleConfigEntity.class, employeeScheduleConfig.getScheduleId());
		if (compScheduleConfig == null) {
			return;
		}

		// 获取下周的第一天
		DateTime firstDayOfNextWeek = getFirstDayOfNextWeek();

		// 删除下周的员工考勤信息
		clearNextWeekEmployeeAttendance(compId, loginId, firstDayOfNextWeek);

		// 生成下周的员工考勤信息
		createNextWeekEmployeeAttendance(compId, loginId, firstDayOfNextWeek, employeeScheduleConfig.getScheduleInfo().split(LKFrameworkStatics.SPLITOR), compScheduleConfig);
	}


	/**
	 * 获取下周第一天
	 */
	public DateTime getFirstDayOfNextWeek() {
		DateTime nextWeekDay = DateTime.now().plusWeeks(1);
		return LKDateTimeUtils.toDateTime(LKCalendarUtils.getFirstDayOfWeek(nextWeekDay.getYear(), nextWeekDay.getWeekOfWeekyear()), LKDateTimeTypeEnum.DATE_ONLY);
	}


	/**
	 * 获取员工排班配置信息
	 */
	private SysEmployeeScheduleConfigEntity getEmployeeScheduleConfigByCompIdAndLoginId(String compId, String loginId) {
		QuerySQL sql = new QuerySQL(SysEmployeeScheduleConfigEntity.class);
		sql.eq(SysEmployeeScheduleConfigR.compId, compId);
		sql.eq(SysEmployeeScheduleConfigR.loginId, loginId);
		return dao.getOne(sql, SysEmployeeScheduleConfigEntity.class);
	}


	/**
	 * 删除下周的员工考勤信息
	 */
	public void clearNextWeekEmployeeAttendance(String compId, String loginId, DateTime firstDayOfNextWeek) {
		DeleteSQL sql = new DeleteSQL(SysEmployeeAttendanceEntity.class);
		sql.eq(SysEmployeeAttendanceR.compId, compId);
		sql.eq(SysEmployeeAttendanceR.loginId, loginId);
		sql.gte(SysEmployeeAttendanceR.workDate, LKDateTimeUtils.toString(firstDayOfNextWeek, LKDateTimeTypeEnum.DATE_ONLY));
		sql.lte(SysEmployeeAttendanceR.workDate, LKDateTimeUtils.toString(firstDayOfNextWeek.plusDays(6), LKDateTimeTypeEnum.DATE_ONLY));
		dao.delete(sql);
	}


	/**
	 * 生成下周的员工考勤信息
	 */
	private void createNextWeekEmployeeAttendance(String compId, String loginId, DateTime firstDayOfNextWeek, String[] schedules, SysCompScheduleConfigEntity compSchedule) {
		List<SysEmployeeAttendanceEntity> list = new ArrayList<>(7);

		for (int i = 0; i < 7; i++) {
			SysEmployeeAttendanceEntity entity = new SysEmployeeAttendanceEntity();

			entity.setCompId(compId);
			entity.setLoginId(loginId);

			entity.setTakeWorkingDayOff(false);
			entity.setAskForLeave(false);
			entity.setAbsenteeism(false);
			entity.setBeLate(false);
			entity.setLeaveEarly(false);

			String day = LKDateTimeUtils.toString(firstDayOfNextWeek.plusDays(i), LKDateTimeTypeEnum.DATE_ONLY);
			entity.setWorkDate(day);
			String nextDay = LKDateTimeUtils.toString(firstDayOfNextWeek.plusDays(i + 1), LKDateTimeTypeEnum.DATE_ONLY);

			String schedule = schedules[i];

			if ("-1".equals(schedule)) {
				// 休息日
				entity.setDayOff(true);

				entity.setStartTime(null);
				entity.setEndTime(null);

				entity.setAllowBeforeStartTime(null);
				entity.setAllowAfterStartTime(null);

				entity.setAllowBeforeEndTime(null);
				entity.setAllowAfterEndTime(null);
			} else {
				// 工作日
				entity.setDayOff(false);

				String startTime = "";
				String endTime = "";

				switch (schedule) {
					case "0":
						startTime = compSchedule.getStartTime0();
						endTime = compSchedule.getEndTime0();
					break;
					case "1":
						startTime = compSchedule.getStartTime1();
						endTime = compSchedule.getEndTime1();
					break;
					case "2":
						startTime = compSchedule.getStartTime2();
						endTime = compSchedule.getEndTime2();
					break;
				}

				entity.setStartTime(day + " " + startTime + ":00");
				entity.setEndTime((Integer.parseInt(startTime.split(":")[0]) > Integer.parseInt(endTime.split(":")[0]) ? nextDay : day) + " " + endTime + ":00");

				DateTime startDateTime = LKDateTimeUtils.toDateTime(entity.getStartTime(), LKDateTimeTypeEnum.STANDARD);
				entity.setAllowBeforeStartTime(LKDateTimeUtils.toString(startDateTime.minusMinutes(compSchedule.getAllowBeforeStartTimeMinutes()), LKDateTimeTypeEnum.STANDARD));
				entity.setAllowAfterStartTime(LKDateTimeUtils.toString(startDateTime.plusMinutes(compSchedule.getAllowAfterStartTimeMinutes()), LKDateTimeTypeEnum.STANDARD));

				DateTime endDateTime = LKDateTimeUtils.toDateTime(entity.getEndTime(), LKDateTimeTypeEnum.STANDARD);
				entity.setAllowBeforeEndTime(LKDateTimeUtils.toString(endDateTime.minusMinutes(compSchedule.getAllowBeforeEndTimeMinutes()), LKDateTimeTypeEnum.STANDARD));
				entity.setAllowAfterEndTime(LKDateTimeUtils.toString(endDateTime.plusMinutes(compSchedule.getAllowAfterEndTimeMinutes()), LKDateTimeTypeEnum.STANDARD));
			}

			list.add(entity);
		}

		dao.persistList(list);
	}

}