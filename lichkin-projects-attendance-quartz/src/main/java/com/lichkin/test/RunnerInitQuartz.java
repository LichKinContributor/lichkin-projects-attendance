package com.lichkin.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.lichkin.application.services.impl.quartz.CreateEmployeeScheduleJobService;
import com.lichkin.application.services.impl.quartz.HandleCompAttendanceJobService;
import com.lichkin.application.services.impl.quartz.HandleEmployeeLeaveAndRestJobService;
import com.lichkin.application.services.impl.quartz.HandleEmployeeScheduleJobService;
import com.lichkin.framework.springboot.LKJobInfo;
import com.lichkin.framework.springboot.LKTaskRunner4InitSysConfigQuartz;

@Configuration
@Order(value = 1)
public class RunnerInitQuartz extends LKTaskRunner4InitSysConfigQuartz {

	@Override
	protected List<LKJobInfo> getJobs() {
		final List<LKJobInfo> list = new ArrayList<>();

		// 创建公司员工排班(每周一)
		list.add(new LKJobInfo(CreateEmployeeScheduleJobService.class, "0 0 5 ? * MON"));

		// 处理未产生的员工排班(每周二、五)
		list.add(new LKJobInfo(HandleEmployeeScheduleJobService.class, "0 0 5 ? * TUE,FRI"));

		// 处理请假、调休信息（每天凌晨处理已经审批通过的信息）
		list.add(new LKJobInfo(HandleEmployeeLeaveAndRestJobService.class, "0 0 4 * * ?"));

		// 处理公司所有员工旷工的信息（每天晚上处理昨天以及以前的）
		list.add(new LKJobInfo(HandleCompAttendanceJobService.class, "0 0 21 * * ?"));

		return list;
	}

}