package com.lichkin.application.services.activiti.impl;

import org.springframework.stereotype.Service;

import com.lichkin.application.services.ActivitiCallbackService;
import com.lichkin.framework.db.beans.SysEmployeeR;
import com.lichkin.framework.db.beans.UpdateSQL;
import com.lichkin.springframework.entities.impl.SysActivitiFormDataEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeEntity;
import com.lichkin.springframework.services.LKDBService;

@Service("DISMISSION")
public class ActivitiCallbackService_DISMISSION extends LKDBService implements ActivitiCallbackService {

	@Override
	public void approve(SysActivitiFormDataEntity formDataEntity, byte step) {
	}


	@Override
	public void finish(SysActivitiFormDataEntity formDataEntity) {
		UpdateSQL sql = new UpdateSQL(SysEmployeeEntity.class);
		sql.eq(SysEmployeeR.compId, formDataEntity.getCompId());
		sql.eq(SysEmployeeR.loginId, formDataEntity.getApproverLoginId());
		dao.update(sql);
	}


	@Override
	public void reject(SysActivitiFormDataEntity formDataEntity) {
	}

}
