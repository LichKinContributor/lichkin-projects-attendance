package com.lichkin.application.apis.PunchTheClock;

import com.lichkin.defines.beans.LKAMapLocation;
import com.lichkin.framework.beans.impl.LKRequestBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

	/** 高德定位信息 */
	private LKAMapLocation amap;

}
