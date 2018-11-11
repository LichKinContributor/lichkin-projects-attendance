package com.lichkin.application.apis.api40001.I.n00;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysCompScheduleConfigBusService;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.springframework.entities.impl.SysCompScheduleConfigEntity;
import com.lichkin.springframework.services.LKApiBusInsertService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("SysCompScheduleConfigI00Service")
public class S extends LKApiBusInsertService<I, SysCompScheduleConfigEntity> {

	@Autowired
	private SysCompScheduleConfigBusService busService;


	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysCompScheduleConfig_EXIST(50000),

		;

		private final Integer code;

	}


	@Override
	protected List<SysCompScheduleConfigEntity> findExist(I sin, String locale, String compId, String loginId) {
		return busService.findExist(null, compId, sin.getCompId(), sin.getScheduleName());
	}


	@Override
	protected boolean forceCheck(I sin, String locale, String compId, String loginId) {
		return true;
	}


	@Override
	protected LKCodeEnum existErrorCode(I sin, String locale, String compId, String loginId) {
		return ErrorCodes.SysCompScheduleConfig_EXIST;
	}


	@Override
	protected void beforeAddNew(I sin, String locale, String compId, String loginId, SysCompScheduleConfigEntity entity) {
		entity.setCompId(getCompId(compId, sin.getCompId()));
	}

}
