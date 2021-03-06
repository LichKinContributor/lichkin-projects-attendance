package com.lichkin.application.apis.api40003.S.n00;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.application.services.impl.SysEmployeeAttendanceService;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.services.LKApiVoidService;

@Service("SysEmployeeAttendanceS00Service")
public class S implements LKApiVoidService<I> {

	@Autowired
	private SysEmployeeAttendanceService employeeAttendanceService;


	@Transactional
	@Override
	public void handle(I sin, ApiKeyValues<I> params) throws LKException {
		if (sin.getId().contains(LKFrameworkStatics.SPLITOR)) {
			String[] ids = sin.getId().split(LKFrameworkStatics.SPLITOR);
			for (String id : ids) {
				employeeAttendanceService.resetNextWeekEmployeeAttendance(params.getCompId(), id);
			}
		} else {
			employeeAttendanceService.resetNextWeekEmployeeAttendance(params.getCompId(), sin.getId());
		}
	}

}
