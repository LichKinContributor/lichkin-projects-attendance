package com.lichkin.application.apis.api40004.P.n00;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class O {

	private String id;

	private String usingStatus;

	private String usingStatusDictCode;// for usingStatus

	private String insertTime;

	private String appKey;

	private String clientType;

	private String clientTypeDictCode;// for clientType

	private Byte versionX;

	private Byte versionY;

	private Short versionZ;

	private Double latitude;

	private Double longitude;

	private Double altitude;

	private String address;

	/** 员工姓名 */
	private String userName;

	/** 员工手机号码 */
	private String cellphone;

}
