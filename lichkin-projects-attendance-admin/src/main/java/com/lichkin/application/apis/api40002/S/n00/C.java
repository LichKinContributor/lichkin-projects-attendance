package com.lichkin.application.apis.api40002.S.n00;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.LKApiYYController;
import com.lichkin.springframework.services.LKApiService;

@RestController("SysEmployeeScheduleConfigS00Controller")
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API + "/SysEmployeeScheduleConfig/S")
@LKApiType(apiType = ApiType.COMPANY_BUSINESS)
public class C extends LKApiYYController<I, List<O>, I, List<O>> {

	@Autowired
	private S service;


	@Override
	protected LKApiService<I, List<O>> getService(I cin) {
		return service;
	}


	@Override
	protected I beforeInvokeService(I cin) throws LKException {
		return cin;
	}


	@Override
	protected List<O> afterInvokeService(I cin, I sin, List<O> sout) throws LKException {
		return sout;
	}

}
