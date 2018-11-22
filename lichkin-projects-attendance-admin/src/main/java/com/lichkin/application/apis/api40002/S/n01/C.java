package com.lichkin.application.apis.api40002.S.n01;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.utils.LKBeanUtils;
import com.lichkin.framework.utils.i18n.LKI18NUtils;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.LKApiYYController;
import com.lichkin.springframework.services.LKApiService;

@RestController("SysEmployeeScheduleConfigS01Controller")
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API + "/SysEmployeeScheduleConfig/S01")
@LKApiType(apiType = ApiType.COMPANY_BUSINESS)
public class C extends LKApiYYController<I, CO, I, SO> {

	@Autowired
	private S service;


	@Override
	protected LKApiService<I, SO> getService(I cin) {
		return service;
	}


	@Override
	protected I beforeInvokeService(I cin) throws LKException {
		return cin;
	}


	@Override
	protected CO afterInvokeService(I cin, I sin, SO sout) throws LKException {
		CO cout = LKBeanUtils.newInstance(sout, CO.class);

		String scheduleInfo = sout.getScheduleInfo();
		if (StringUtils.isNotBlank(scheduleInfo)) {
			String dayOff = LKI18NUtils.getString(LKI18NUtils.getLocale(sin.getDatas().getLocale()), "app-attendance-api-extends", "com.lichkin.api.dayOff");

			String[] times = scheduleInfo.split(LKFrameworkStatics.SPLITOR);
			List<String> scheduleTime = new ArrayList<>(times.length);
			for (String time : times) {
				switch (time) {
					case "-1":
						scheduleTime.add(dayOff);
					break;
					case "0":
						scheduleTime.add(sout.getStartTime0() + "~" + sout.getEndTime0());
					break;
					case "1":
						scheduleTime.add(sout.getStartTime1() + "~" + sout.getEndTime1());
					break;
					case "2":
						scheduleTime.add(sout.getStartTime2() + "~" + sout.getEndTime2());
					break;
				}
			}
			cout.setScheduleTime(scheduleTime);
		}

		return cout;
	}

}
