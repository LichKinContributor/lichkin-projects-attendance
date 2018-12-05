package com.lichkin.application.apis.api40005.S.n01;

import com.lichkin.framework.beans.impl.LKRequestBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

	private Double latitude;

	private Double longitude;

	private Double altitude;

	private Integer radius;

	private Byte ogham;

}
