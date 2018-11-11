package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.lichkin.framework.defines.annotations.ClassGenerator;
import com.lichkin.framework.defines.annotations.DefaultShortValue;
import com.lichkin.framework.defines.annotations.FieldGenerator;
import com.lichkin.framework.defines.annotations.InsertCheckType;
import com.lichkin.framework.defines.annotations.UpdateCheckType;
import com.lichkin.framework.defines.enums.impl.ScheduleTypeEnum;
import com.lichkin.springframework.entities.suppers.BaseCompEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 公司排班配置表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
@ClassGenerator(afterSaveMain = true, insertCheckType = InsertCheckType.FORCE_CHECK, updateCheckType = UpdateCheckType.CHECK, IUSubTables = {})
public class SysCompScheduleConfigEntity extends BaseCompEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 40001L;

	/** 排班类型（枚举） */
	@Enumerated(EnumType.STRING)
	@FieldGenerator(updateable = false, queryCondition = true, queryConditionLike = false, resultColumn = true)
	@Column(length = 7, nullable = false)
	private ScheduleTypeEnum scheduleType;

	/** 排班名称 */
	@FieldGenerator(check = true, queryCondition = true, resultColumn = true)
	@Column(length = 16, nullable = false)
	private String scheduleName;

	/** 上班时间（HH:mm） */
	@FieldGenerator(resultColumn = true)
	@Column(length = 5, nullable = false)
	private String startTime0;

	/** 下班时间（HH:mm） */
	@FieldGenerator(resultColumn = true)
	@Column(length = 5, nullable = false)
	private String endTime0;

	/** 上班时间（HH:mm） */
	@FieldGenerator(resultColumn = true)
	@Column(length = 5)
	private String startTime1;

	/** 下班时间（HH:mm） */
	@FieldGenerator(resultColumn = true)
	@Column(length = 5)
	private String endTime1;

	/** 上班时间（HH:mm） */
	@FieldGenerator(resultColumn = true)
	@Column(length = 5)
	private String startTime2;

	/** 下班时间（HH:mm） */
	@FieldGenerator(resultColumn = true)
	@Column(length = 5)
	private String endTime2;

	/** 上班允许提前打卡时间（分钟） */
	@DefaultShortValue(value = 60)
	@FieldGenerator(resultColumn = true)
	@Column
	private Short allowBeforeStartTimeMinutes;

	/** 上班允许延迟打卡时间（分钟） */
	@DefaultShortValue
	@FieldGenerator(resultColumn = true)
	@Column
	private Short allowAfterStartTimeMinutes;

	/** 下班允许提前打卡时间（分钟） */
	@DefaultShortValue
	@FieldGenerator(resultColumn = true)
	@Column
	private Short allowBeforeEndTimeMinutes;

	/** 下班允许延迟打卡时间（分钟） */
	@DefaultShortValue(value = 60)
	@FieldGenerator(resultColumn = true)
	@Column
	private Short allowAfterEndTimeMinutes;

	/** 备注 */
	@FieldGenerator(resultColumn = true)
	@Column(length = 512)
	private String remarks;

}
