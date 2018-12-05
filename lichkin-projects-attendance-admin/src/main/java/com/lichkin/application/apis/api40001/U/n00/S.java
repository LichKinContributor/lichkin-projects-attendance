package com.lichkin.application.apis.api40001.U.n00;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.impl.SysEmployeeAttendanceService;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysEmployeeScheduleConfigR;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysCompScheduleConfigEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeScheduleConfigEntity;
import com.lichkin.springframework.services.LKApiBusUpdateWithoutCheckerService;

@Service("SysCompScheduleConfigU00Service")
public class S extends LKApiBusUpdateWithoutCheckerService<I, SysCompScheduleConfigEntity> {

	@Autowired
	private SysEmployeeAttendanceService employeeAttendanceService;


	@Override
	protected void afterSaveMain(I sin, ApiKeyValues<I> params, SysCompScheduleConfigEntity entity, String id) {
		QuerySQL sql = new QuerySQL(false, SysEmployeeScheduleConfigEntity.class);
		sql.eq(SysEmployeeScheduleConfigR.compId, entity.getCompId());
		sql.eq(SysEmployeeScheduleConfigR.scheduleId, entity.getId());
		List<SysEmployeeScheduleConfigEntity> list = dao.getList(sql, SysEmployeeScheduleConfigEntity.class);
		for (SysEmployeeScheduleConfigEntity e : list) {
			employeeAttendanceService.resetNextWeekEmployeeAttendance(e.getCompId(), e.getEmployeeId());
		}
	}

}
