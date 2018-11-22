package com.lichkin.application.apis.PunchTheClock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.LKApiBusInsertController;
import com.lichkin.springframework.entities.impl.SysEmployeePunchTheClockEntity;
import com.lichkin.springframework.services.LKApiBusInsertWithoutCheckerService;

@RestController(Statics.CONTROLLER_NAME)
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API + Statics.SUB_URL)
@LKApiType(apiType = ApiType.COMPANY_BUSINESS)
public class C extends LKApiBusInsertController<I, SysEmployeePunchTheClockEntity> {

	@Autowired
	private S service;


	@Override
	protected LKApiBusInsertWithoutCheckerService<I, SysEmployeePunchTheClockEntity> getService(I cin) {
		return service;
	}

}
