package com.lichkin.application.apis.api40002.S.n00;

import com.lichkin.framework.beans.impl.LKRequestPageBean;
import com.lichkin.framework.constraints.Text;
import com.lichkin.framework.defines.enums.impl.ScheduleTypeEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestPageBean {

	/** 员工姓名 */
	@Text
	private String userName;

	/** 排班类型 */
	private ScheduleTypeEnum scheduleType;

}
