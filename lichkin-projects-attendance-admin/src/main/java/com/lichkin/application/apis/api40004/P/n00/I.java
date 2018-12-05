package com.lichkin.application.apis.api40004.P.n00;

import com.lichkin.framework.beans.impl.LKRequestPageBean;
import com.lichkin.framework.defines.enums.impl.LKClientTypeEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestPageBean {

	private String appKey;

	private LKClientTypeEnum clientType;

	/** 员工姓名 */
	private String userName;

	/** 员工手机号码 */
	private String cellphone;

}
