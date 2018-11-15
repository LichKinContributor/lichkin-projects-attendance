package com.lichkin.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.lichkin.application.services.impl.quartz.CreateEmployeeScheduleJobService;
import com.lichkin.application.services.impl.quartz.HandleCompAttendanceJobService;
import com.lichkin.application.services.impl.quartz.HandleEmployeeLeaveAndRestJobService;
import com.lichkin.application.services.impl.quartz.HandleEmployeeScheduleJobService;
import com.lichkin.framework.springboot.LKDBJobInfo;
import com.lichkin.framework.springboot.LKTaskRunner4InitSysConfigQuartz;

@Configuration
@Order(value = 1)
public class RunnerInitQuartz extends LKTaskRunner4InitSysConfigQuartz {

	@Override
	protected List<LKDBJobInfo> getJobs() {
		final List<LKDBJobInfo> list = new ArrayList<>();

		// 创建公司员工排班(每周一)
		list.add(new LKDBJobInfo(CreateEmployeeScheduleJobService.class, "0 0 5 ? * MON"));

		// 处理未产生的员工排班(每周二、五)
		list.add(new LKDBJobInfo(HandleEmployeeScheduleJobService.class, "0 0 5 ? * TUE,FRI"));

		// 处理请假、调休信息（每天凌晨处理已经审批通过的信息）
		// 忽略上线延迟导致的数据问题。
		// 本任务延迟执行两天处理考勤，以确保考勤处理正确。
		// 首次执行任务，处理前天的考勤。
		// 后续执行任务，顺延一天，故取上次执行时间的前一天。
		list.add(new LKDBJobInfo(HandleEmployeeLeaveAndRestJobService.class, "0 0 4 * * ?"));

		// 处理公司所有员工旷工的信息
		// 忽略上线延迟导致的数据问题。
		// 本任务延迟执行两天处理考勤，以确保考勤处理正确。
		// 首次执行任务，处理前天的考勤。
		// 后续执行任务，顺延一天，故取上次执行时间的前一天。
		list.add(new LKDBJobInfo(HandleCompAttendanceJobService.class, "0 0 1 * * ?"));

		return list;
	}

}