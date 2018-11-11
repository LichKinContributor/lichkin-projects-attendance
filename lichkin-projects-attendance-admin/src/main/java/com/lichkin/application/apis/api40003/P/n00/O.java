package com.lichkin.application.apis.api40003.P.n00;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class O {

	private String id;

	private String usingStatus;

	private String usingStatusDictCode;// for usingStatus

	private String insertTime;

	private String workDate;

	private String startTime;

	private String endTime;

	private Boolean dayOff;

	private Boolean takeWorkingDayOff;

	private Boolean askForLeave;

	private Boolean absenteeism;

	private Boolean beLate;

	private Boolean leaveEarly;

	private String firstPunchTime;

	private String lastPunchTime;

	/** 员工姓名 */
	private String userName;

	/** 员工手机号码 */
	private String cellphone;

}
