package com.lichkin.application.services.impl.quartz;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.lichkin.application.services.impl.SysEmployeeAttendanceService;
import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysCompR;
import com.lichkin.framework.db.beans.SysEmployeeAttendanceR;
import com.lichkin.framework.db.beans.SysEmployeeR;
import com.lichkin.framework.db.beans.SysEmployeeScheduleConfigR;
import com.lichkin.framework.db.beans.eq_;
import com.lichkin.framework.defines.LKConfigStatics;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.springboot.configurations.LKQuartzManager;
import com.lichkin.framework.springboot.services.LKBaseJobService;
import com.lichkin.framework.utils.LKCalendarUtils;
import com.lichkin.springframework.entities.impl.SysCompEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeAttendanceEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeScheduleConfigEntity;
import com.lichkin.springframework.services.impl.SysConfigQuartzBusService;

/**
 * 处理未生成排班的员工
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
public class HandleEmployeeScheduleJobService extends LKBaseJobService {

	/** 日志对象 */
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private SysEmployeeAttendanceService service;

	@Autowired
	private SysConfigQuartzBusService quartzBusService;


	@Override
	protected void doTask() {
		// 查询所有已配置排班的员工
		QuerySQL sql = new QuerySQL(SysEmployeeEntity.class);
		sql.selectTable(SysEmployeeEntity.class);
		sql.innerJoin(SysEmployeeScheduleConfigEntity.class,

				new Condition(new eq_(SysEmployeeR.compId, SysEmployeeScheduleConfigR.compId)),

				new Condition(true, new eq_(SysEmployeeR.id, SysEmployeeScheduleConfigR.loginId))

		);

		sql.innerJoin(SysCompEntity.class, new Condition(SysEmployeeR.compId, SysCompR.id));
		sql.eq(SysEmployeeR.usingStatus, LKUsingStatusEnum.USING);
		sql.eq(SysEmployeeScheduleConfigR.usingStatus, LKUsingStatusEnum.USING);
		sql.eq(SysCompR.usingStatus, LKUsingStatusEnum.USING);
		List<SysEmployeeEntity> listEmployee = dao.getList(sql, SysEmployeeEntity.class);

		// 查询员工下周最后一天的排班（如果有最后一天的排班说明排班已经生成）
		DateTime nowTime = new DateTime().plusWeeks(1);
		String lastDayOfWeek = LKCalendarUtils.getLastDayOfWeek(nowTime.getYear(), nowTime.getWeekOfWeekyear());
		sql = new QuerySQL(false, SysEmployeeAttendanceEntity.class);
		sql.eq(SysEmployeeAttendanceR.workDate, lastDayOfWeek);
		sql.eq(SysEmployeeAttendanceR.usingStatus, LKUsingStatusEnum.USING);
		List<SysEmployeeAttendanceEntity> listEmployeeAttendance = dao.getList(sql, SysEmployeeAttendanceEntity.class);

		// 找出未生成排班的员工
		for (int i = listEmployee.size() - 1; i >= 0; i--) {
			SysEmployeeEntity employee = listEmployee.get(i);
			for (int j = listEmployeeAttendance.size() - 1; j >= 0; j--) {
				SysEmployeeAttendanceEntity employeeAttendance = listEmployeeAttendance.get(j);
				if (employee.getId().equals(employeeAttendance.getLoginId()) && employee.getCompId().equals(employeeAttendance.getCompId())) {
					listEmployee.remove(employee);
					listEmployeeAttendance.remove(employeeAttendance);
					break;
				}
			}
		}

		// 重新生成排班
		if (CollectionUtils.isNotEmpty(listEmployee)) {
			for (SysEmployeeEntity employee : listEmployee) {
				service.resetNextWeekEmployeeAttendance(employee.getCompId(), employee.getId());
			}
			LKQuartzManager.getInstance().rescheduleJob(false, LKConfigStatics.SYSTEM_TAG, "HandleEmployeeScheduleJobService", "0 0 0/2 * * ?");
		} else {
			LKQuartzManager.getInstance().rescheduleJob(false, LKConfigStatics.SYSTEM_TAG, "HandleEmployeeScheduleJobService", "0 0 5 ? * TUE,FRI");
		}

		// 记录任务最后一次执行时间
		quartzBusService.updateJobLastExecutionTime(getClass().getSimpleName());
	}

}
