package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lichkin.framework.defines.annotations.ClassGenerator;
import com.lichkin.framework.defines.annotations.FieldGenerator;
import com.lichkin.framework.defines.annotations.InsertCheckType;
import com.lichkin.framework.defines.annotations.InsertType;
import com.lichkin.framework.defines.annotations.UpdateCheckType;
import com.lichkin.springframework.entities.suppers.BaseCompEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 员工排班配置表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
@ClassGenerator(afterSaveMain = true, insertCheckType = InsertCheckType.UNCHECK, updateCheckType = UpdateCheckType.UNCHECK)
public class SysEmployeeScheduleConfigEntity extends BaseCompEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 40002L;

	/** 员工ID（SysEmployeeEntity.id） */
	@FieldGenerator()
	@Column(length = 64, nullable = false)
	private String loginId;

	/** 公司排班ID（SysCompScheduleConfigEntity.id） */
	@FieldGenerator()
	@Column(length = 64, nullable = false)
	private String scheduleId;

	/** 一周的排班信息（标准分隔符） */
	@FieldGenerator()
	@Column(length = 36, nullable = false)
	private String scheduleInfo;

	/** 最后一次配置时间（yyyyMMddHHmmssSSS） */
	@FieldGenerator(insertType = InsertType.HANDLE_HANDLE)
	@Column(length = 17, nullable = false)
	private String configTime;

}
