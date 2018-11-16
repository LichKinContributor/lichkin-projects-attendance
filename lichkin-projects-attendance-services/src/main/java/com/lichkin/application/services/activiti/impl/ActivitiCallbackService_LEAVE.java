package com.lichkin.application.services.activiti.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.ActivitiCallbackService;
import com.lichkin.application.services.impl.SysEmployeeAttendanceService;
import com.lichkin.springframework.entities.impl.SysActivitiFormDataEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeAttendanceEntity;
import com.lichkin.springframework.services.LKDBService;

@Service("LEAVE")
public class ActivitiCallbackService_LEAVE extends LKDBService implements ActivitiCallbackService {

	@Autowired
	private SysEmployeeAttendanceService attendanceService;


	@Override
	public void approve(SysActivitiFormDataEntity formDataEntity, byte step) {
	}


	@Override
	public void finish(SysActivitiFormDataEntity formDataEntity) {
		List<SysEmployeeAttendanceEntity> listAttendance = attendanceService.getListAttendance(formDataEntity.getApproverLoginId(), formDataEntity.getCompId(), formDataEntity.getField1(), formDataEntity.getField2());
		for (SysEmployeeAttendanceEntity attendance : listAttendance) {
			attendance.setAbsenteeism(false);
			attendance.setAskForLeave(true);
		}
		dao.mergeList(listAttendance);
	}


	@Override
	public void reject(SysActivitiFormDataEntity formDataEntity) {
	}

}
