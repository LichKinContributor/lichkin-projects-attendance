package com.lichkin.application.apis.api40001.LD.n01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.controllers.LKApiBusGetDroplistController;
import com.lichkin.springframework.services.LKApiBusGetDroplistService;

@RestController("SysCompScheduleConfigLD01Controller")
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API + "/SysCompScheduleConfig/LD01")
@LKApiType(apiType = ApiType.COMPANY_BUSINESS)
public class C extends LKApiBusGetDroplistController<I> {

	@Autowired
	private S service;


	@Override
	protected LKApiBusGetDroplistService<I> getService(I cin, ApiKeyValues<I> params) {
		return service;
	}

}
