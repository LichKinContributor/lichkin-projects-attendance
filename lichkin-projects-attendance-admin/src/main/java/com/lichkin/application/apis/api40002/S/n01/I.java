package com.lichkin.application.apis.api40002.S.n01;

import com.lichkin.framework.beans.impl.LKRequestIDBean;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestIDBean {

	private String compId;

	private LKUsingStatusEnum usingStatus;

}
