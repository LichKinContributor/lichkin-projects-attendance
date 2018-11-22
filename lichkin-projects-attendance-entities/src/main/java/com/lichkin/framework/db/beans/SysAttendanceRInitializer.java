package com.lichkin.framework.db.beans;

/**
 * 数据库资源初始化类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
class SysAttendanceRInitializer implements LKRInitializer {

	/**
	 * 初始化数据库资源
	 */
	public static void init() {
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysCompScheduleConfigEntity", "T_SYS_COMP_SCHEDULE_CONFIG", "SysCompScheduleConfigEntity");
		LKDBResource.addColumn("40001000", "SysCompScheduleConfigEntity", "id");
		LKDBResource.addColumn("40001001", "SysCompScheduleConfigEntity", "usingStatus");
		LKDBResource.addColumn("40001002", "SysCompScheduleConfigEntity", "insertTime");
		LKDBResource.addColumn("40001003", "SysCompScheduleConfigEntity", "compId");
		LKDBResource.addColumn("40001004", "SysCompScheduleConfigEntity", "scheduleType");
		LKDBResource.addColumn("40001005", "SysCompScheduleConfigEntity", "scheduleName");
		LKDBResource.addColumn("40001006", "SysCompScheduleConfigEntity", "startTime0");
		LKDBResource.addColumn("40001007", "SysCompScheduleConfigEntity", "endTime0");
		LKDBResource.addColumn("40001008", "SysCompScheduleConfigEntity", "startTime1");
		LKDBResource.addColumn("40001009", "SysCompScheduleConfigEntity", "endTime1");
		LKDBResource.addColumn("40001010", "SysCompScheduleConfigEntity", "startTime2");
		LKDBResource.addColumn("40001011", "SysCompScheduleConfigEntity", "endTime2");
		LKDBResource.addColumn("40001012", "SysCompScheduleConfigEntity", "allowBeforeStartTimeMinutes");
		LKDBResource.addColumn("40001013", "SysCompScheduleConfigEntity", "allowAfterStartTimeMinutes");
		LKDBResource.addColumn("40001014", "SysCompScheduleConfigEntity", "allowBeforeEndTimeMinutes");
		LKDBResource.addColumn("40001015", "SysCompScheduleConfigEntity", "allowAfterEndTimeMinutes");
		LKDBResource.addColumn("40001016", "SysCompScheduleConfigEntity", "remarks");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysEmployeeScheduleConfigEntity", "T_SYS_EMPLOYEE_SCHEDULE_CONFIG", "SysEmployeeScheduleConfigEntity");
		LKDBResource.addColumn("40002000", "SysEmployeeScheduleConfigEntity", "id");
		LKDBResource.addColumn("40002001", "SysEmployeeScheduleConfigEntity", "usingStatus");
		LKDBResource.addColumn("40002002", "SysEmployeeScheduleConfigEntity", "insertTime");
		LKDBResource.addColumn("40002003", "SysEmployeeScheduleConfigEntity", "compId");
		LKDBResource.addColumn("40002004", "SysEmployeeScheduleConfigEntity", "loginId");
		LKDBResource.addColumn("40002005", "SysEmployeeScheduleConfigEntity", "scheduleId");
		LKDBResource.addColumn("40002006", "SysEmployeeScheduleConfigEntity", "scheduleInfo");
		LKDBResource.addColumn("40002007", "SysEmployeeScheduleConfigEntity", "configTime");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysEmployeeAttendanceEntity", "T_SYS_EMPLOYEE_ATTENDANCE", "SysEmployeeAttendanceEntity");
		LKDBResource.addColumn("40003000", "SysEmployeeAttendanceEntity", "id");
		LKDBResource.addColumn("40003001", "SysEmployeeAttendanceEntity", "usingStatus");
		LKDBResource.addColumn("40003002", "SysEmployeeAttendanceEntity", "insertTime");
		LKDBResource.addColumn("40003003", "SysEmployeeAttendanceEntity", "compId");
		LKDBResource.addColumn("40003004", "SysEmployeeAttendanceEntity", "loginId");
		LKDBResource.addColumn("40003005", "SysEmployeeAttendanceEntity", "workDate");
		LKDBResource.addColumn("40003006", "SysEmployeeAttendanceEntity", "allowBeforeStartTime");
		LKDBResource.addColumn("40003007", "SysEmployeeAttendanceEntity", "startTime");
		LKDBResource.addColumn("40003008", "SysEmployeeAttendanceEntity", "allowAfterStartTime");
		LKDBResource.addColumn("40003009", "SysEmployeeAttendanceEntity", "allowBeforeEndTime");
		LKDBResource.addColumn("40003010", "SysEmployeeAttendanceEntity", "endTime");
		LKDBResource.addColumn("40003011", "SysEmployeeAttendanceEntity", "allowAfterEndTime");
		LKDBResource.addColumn("40003012", "SysEmployeeAttendanceEntity", "dayOff");
		LKDBResource.addColumn("40003013", "SysEmployeeAttendanceEntity", "takeWorkingDayOff");
		LKDBResource.addColumn("40003014", "SysEmployeeAttendanceEntity", "askForLeave");
		LKDBResource.addColumn("40003015", "SysEmployeeAttendanceEntity", "absenteeism");
		LKDBResource.addColumn("40003016", "SysEmployeeAttendanceEntity", "beLate");
		LKDBResource.addColumn("40003017", "SysEmployeeAttendanceEntity", "leaveEarly");
		LKDBResource.addColumn("40003018", "SysEmployeeAttendanceEntity", "firstPunchTime");
		LKDBResource.addColumn("40003019", "SysEmployeeAttendanceEntity", "lastPunchTime");
		LKDBResource.addColumn("40003020", "SysEmployeeAttendanceEntity", "remarks");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysEmployeePunchTheClockEntity", "T_SYS_EMPLOYEE_PUNCH_THE_CLOCK", "SysEmployeePunchTheClockEntity");
		LKDBResource.addColumn("40004000", "SysEmployeePunchTheClockEntity", "id");
		LKDBResource.addColumn("40004001", "SysEmployeePunchTheClockEntity", "usingStatus");
		LKDBResource.addColumn("40004002", "SysEmployeePunchTheClockEntity", "insertTime");
		LKDBResource.addColumn("40004003", "SysEmployeePunchTheClockEntity", "appKey");
		LKDBResource.addColumn("40004004", "SysEmployeePunchTheClockEntity", "clientType");
		LKDBResource.addColumn("40004005", "SysEmployeePunchTheClockEntity", "versionX");
		LKDBResource.addColumn("40004006", "SysEmployeePunchTheClockEntity", "versionY");
		LKDBResource.addColumn("40004007", "SysEmployeePunchTheClockEntity", "versionZ");
		LKDBResource.addColumn("40004008", "SysEmployeePunchTheClockEntity", "userId");
		LKDBResource.addColumn("40004009", "SysEmployeePunchTheClockEntity", "mapType");
		LKDBResource.addColumn("40004010", "SysEmployeePunchTheClockEntity", "latitude");
		LKDBResource.addColumn("40004011", "SysEmployeePunchTheClockEntity", "longitude");
		LKDBResource.addColumn("40004012", "SysEmployeePunchTheClockEntity", "altitude");
		LKDBResource.addColumn("40004013", "SysEmployeePunchTheClockEntity", "address");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysCompAttendanceAreaConfigEntity", "T_SYS_COMP_ATTENDANCE_AREA_CONFIG", "SysCompAttendanceAreaConfigEntity");
		LKDBResource.addColumn("40005000", "SysCompAttendanceAreaConfigEntity", "id");
		LKDBResource.addColumn("40005001", "SysCompAttendanceAreaConfigEntity", "compId");
		LKDBResource.addColumn("40005002", "SysCompAttendanceAreaConfigEntity", "latitude");
		LKDBResource.addColumn("40005003", "SysCompAttendanceAreaConfigEntity", "longitude");
		LKDBResource.addColumn("40005004", "SysCompAttendanceAreaConfigEntity", "altitude");
		LKDBResource.addColumn("40005005", "SysCompAttendanceAreaConfigEntity", "radius");
		LKDBResource.addColumn("40005006", "SysCompAttendanceAreaConfigEntity", "ogham");
	}

}