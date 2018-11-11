package com.lichkin.application.apis.api40002.S.n01;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CO {

	/** 姓名 */
	private String userName;

	/** 考勤名称 */
	private String scheduleName;

	/** 考勤备注 */
	private String remarks;

	/** 考勤配置信息 */
	private String employeeSchedule;

	/** 考勤时间列表 */
	private List<String> scheduleTime;

}
