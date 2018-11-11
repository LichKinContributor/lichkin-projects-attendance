package com.lichkin.application.apis.api40002.D.n00;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.impl.SysEmployeeAttendanceService;
import com.lichkin.framework.db.beans.SysEmployeeScheduleConfigR;
import com.lichkin.springframework.entities.impl.SysEmployeeScheduleConfigEntity;
import com.lichkin.springframework.services.LKApiBusDeleteService;

@Service("SysEmployeeScheduleConfigD00Service")
public class S extends LKApiBusDeleteService<I, SysEmployeeScheduleConfigEntity> {

	@Override
	protected int getIdColumnResId() {
		return SysEmployeeScheduleConfigR.id;
	}


	@Autowired
	private SysEmployeeAttendanceService employeeAttendanceService;


	@Override
	protected boolean realDelete(I sin, String locale, String compId, String loginId) {
		return true;
	}


	@Override
	protected void beforeRealDelete(I sin, String locale, String compId, String loginId, SysEmployeeScheduleConfigEntity entity, String id) {
		employeeAttendanceService.clearNextWeekEmployeeAttendance(entity.getCompId(), entity.getLoginId(), employeeAttendanceService.getFirstDayOfNextWeek());
	}

}
