package com.lichkin.application.apis.api40001.U.n00;

import com.lichkin.framework.beans.impl.LKRequestIDBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestIDBean {

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
