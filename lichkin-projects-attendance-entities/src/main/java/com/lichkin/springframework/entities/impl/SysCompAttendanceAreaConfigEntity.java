package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lichkin.springframework.entities.suppers.CompIDEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 公司考勤范围配置表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysCompAttendanceAreaConfigEntity extends CompIDEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 40005L;

	/** 纬度 */
	@Column(nullable = false)
	private Double latitude;

	/** 经度 */
	@Column(nullable = false)
	private Double longitude;

	/** 高度 */
	@Column
	private Double altitude;

	/** 水平面半径 */
	@Column(nullable = false)
	private Byte radius;

	/** 垂直面落差 */
	@Column
	private Byte ogham;

}
