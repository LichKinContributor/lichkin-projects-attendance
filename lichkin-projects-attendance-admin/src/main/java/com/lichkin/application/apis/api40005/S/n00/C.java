package com.lichkin.application.apis.api40005.S.n00;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.controllers.LKApiYYController;
import com.lichkin.springframework.services.LKApiService;

@RestController("SysCompAttendanceAreaConfigS00Controller")
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API + "/SysCompAttendanceAreaConfig/S")
@LKApiType(apiType = ApiType.COMPANY_BUSINESS)
public class C extends LKApiYYController<I, O, O> {

	@Autowired
	private S service;


	@Override
	protected LKApiService<I, O> getService(I cin, ApiKeyValues<I> params) {
		return service;
	}


	@Override
	protected O afterInvokeService(I cin, ApiKeyValues<I> params, O sout) throws LKException {
		return sout;
	}

}
