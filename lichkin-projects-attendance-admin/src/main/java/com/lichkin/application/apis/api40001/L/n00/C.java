package com.lichkin.application.apis.api40001.L.n00;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.LKApiBusGetListController;
import com.lichkin.springframework.entities.impl.SysCompScheduleConfigEntity;
import com.lichkin.springframework.services.LKApiBusGetListService;

@RestController("SysCompScheduleConfigL00Controller")
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API_WEB + "/SysCompScheduleConfig/L")
@LKApiType(apiType = ApiType.COMPANY_BUSINESS)
public class C extends LKApiBusGetListController<I, O, SysCompScheduleConfigEntity> {

	@Autowired
	private S service;


	@Override
	protected LKApiBusGetListService<I, O, SysCompScheduleConfigEntity> getService(I cin) {
		return service;
	}

}
