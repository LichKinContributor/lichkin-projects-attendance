package com.lichkin.application.apis.api40001.O.n00;

import com.lichkin.framework.defines.enums.impl.ScheduleTypeEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class O {

	private String id;

//	private LKUsingStatusEnum usingStatus;

//	private String insertTime;

	private String compId;

	private ScheduleTypeEnum scheduleType;

	private String scheduleName;

	private String startTime0;

	private String endTime0;

	private String startTime1;

	private String endTime1;

	private String startTime2;

	private String endTime2;

	private Short allowBeforeStartTimeMinutes;

	private Short allowAfterStartTimeMinutes;

	private Short allowBeforeEndTimeMinutes;

	private Short allowAfterEndTimeMinutes;

	private String remarks;

}
