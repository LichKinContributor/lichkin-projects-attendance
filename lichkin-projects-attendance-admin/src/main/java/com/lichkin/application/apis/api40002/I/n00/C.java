package com.lichkin.application.apis.api40002.I.n00;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.controllers.LKApiVVController;
import com.lichkin.springframework.services.LKApiVoidService;

@RestController("SysEmployeeScheduleConfigI00Controller")
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API + "/SysEmployeeScheduleConfig/I")
@LKApiType(apiType = ApiType.COMPANY_BUSINESS)
public class C extends LKApiVVController<I> {

	@Autowired
	private S service;


	@Override
	protected LKApiVoidService<I> getService(I cin, ApiKeyValues<I> params) {
		return service;
	}

}
