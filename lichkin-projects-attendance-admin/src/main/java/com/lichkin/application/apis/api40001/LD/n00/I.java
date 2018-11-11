package com.lichkin.application.apis.api40001.LD.n00;

import com.lichkin.framework.beans.impl.LKRequestBean;
import com.lichkin.framework.defines.enums.impl.ScheduleTypeEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

	/** 排班类型 */
	private ScheduleTypeEnum scheduleType;

}
