package com.lichkin.application.apis.api40001.US.n00;

import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.SysCompScheduleConfigR;
import com.lichkin.springframework.entities.impl.SysCompScheduleConfigEntity;
import com.lichkin.springframework.services.LKApiBusUpdateUsingStatusService;

@Service("SysCompScheduleConfigUS00Service")
public class S extends LKApiBusUpdateUsingStatusService<I, SysCompScheduleConfigEntity> {

	@Override
	protected int getIdColumnResId() {
		return SysCompScheduleConfigR.id;
	}

}
