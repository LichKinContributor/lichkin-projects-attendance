package com.lichkin.application.services.bus.impl;

import org.springframework.stereotype.Service;

import com.lichkin.framework.utils.LKDateTimeUtils;

@Service
public class SysEmployeeScheduleConfigBusService {

	public String analysisConfigTime() {
		return LKDateTimeUtils.now();
	}

}
