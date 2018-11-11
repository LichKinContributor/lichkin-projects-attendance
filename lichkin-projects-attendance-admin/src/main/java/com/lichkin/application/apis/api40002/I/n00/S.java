package com.lichkin.application.apis.api40002.I.n00;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysEmployeeScheduleConfigBusService;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.springframework.entities.impl.SysEmployeeScheduleConfigEntity;
import com.lichkin.springframework.services.LKApiBusInsertWithoutCheckerService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("SysEmployeeScheduleConfigI00Service")
public class S extends LKApiBusInsertWithoutCheckerService<I, SysEmployeeScheduleConfigEntity> {

	@Autowired
	private SysEmployeeScheduleConfigBusService busService;


	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		;

		private final Integer code;

	}


	@Override
	protected void beforeAddNew(I sin, String locale, String compId, String loginId, SysEmployeeScheduleConfigEntity entity) {
		entity.setCompId(getCompId(compId, sin.getCompId()));
	}


	@Override
	protected void beforeSaveMain(I sin, String locale, String compId, String loginId, SysEmployeeScheduleConfigEntity entity) {
		entity.setConfigTime(busService.analysisConfigTime());
	}

}