package com.lichkin.application.apis.api40001.L.n00;

import com.lichkin.framework.beans.impl.LKRequestBean;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.enums.impl.ScheduleTypeEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

	private LKUsingStatusEnum usingStatus;

	private String compId;

	private ScheduleTypeEnum scheduleType;

	private String scheduleName;

}
