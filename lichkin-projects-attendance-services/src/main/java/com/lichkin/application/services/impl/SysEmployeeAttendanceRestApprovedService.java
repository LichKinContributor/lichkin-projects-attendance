package com.lichkin.application.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.ActivitiApprovedService;
import com.lichkin.springframework.entities.impl.SysActivitiFormDataEntity;
import com.lichkin.springframework.services.LKDBService;

@Service("REST")
public class SysEmployeeAttendanceRestApprovedService extends LKDBService implements ActivitiApprovedService {

	@Autowired
	private SysEmployeeAttendanceService service;


	@Override
	public void handleBusiness(SysActivitiFormDataEntity formDataEntity) {
		service.handAttendanceLeaveAndRest(formDataEntity.getApproverLoginId(), formDataEntity.getCompId(), formDataEntity.getProcessCode(), formDataEntity.getField1(), formDataEntity.getField2());
	}

}
