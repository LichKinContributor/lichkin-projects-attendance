package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.lichkin.framework.defines.annotations.ClassGenerator;
import com.lichkin.framework.defines.annotations.FieldGenerator;
import com.lichkin.framework.defines.annotations.InsertCheckType;
import com.lichkin.framework.defines.annotations.UpdateCheckType;
import com.lichkin.framework.defines.enums.impl.LKMapAPIEnum;
import com.lichkin.springframework.entities.suppers.BaseAppEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 员工打卡记录表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
@ClassGenerator(

		afterSaveMain = false

		, insertCheckType = InsertCheckType.UNCHECK

		, updateCheckType = UpdateCheckType.UNCHECK

		, pageResultColumns = {

				"String userName 员工姓名 SysEmployeeR"

				, "String cellphone 员工手机号码 SysEmployeeR"

		}

		, pageQueryConditions = {

				"String userName 员工姓名 SysEmployeeR"

				, "String cellphone 员工手机号码 SysEmployeeR"

				, "String startDate 开始日期 #entityR"

				, "String endDate 结束日期 #entityR"

		})
public class SysEmployeePunchTheClockEntity extends BaseAppEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 40004L;

	/** 员工ID（SysEmployeeEntity.id） */
	@FieldGenerator()
	@Column(length = 64, nullable = false)
	private String userId;

	/** 地图API类型（枚举） */
	@Enumerated(EnumType.STRING)
	@FieldGenerator()
	@Column(length = 4, nullable = false)
	private LKMapAPIEnum mapType;

	/** 纬度 */
	@FieldGenerator(resultColumn = true)
	@Column(nullable = false)
	private Double latitude;

	/** 经度 */
	@FieldGenerator(resultColumn = true)
	@Column(nullable = false)
	private Double longitude;

	/** 高度 */
	@FieldGenerator(resultColumn = true)
	@Column(nullable = false)
	private Double altitude;

	/** 地址 */
	@FieldGenerator(resultColumn = true)
	@Column(length = 1024, nullable = false)
	private String address;


	/**
	 * 设置地址
	 * @param address 地址
	 */
	public void setAddress(String address) {
		if (address.length() > 1021) {
			address = address.substring(0, 1021) + "...";
		}
		this.address = address;
	}

}
