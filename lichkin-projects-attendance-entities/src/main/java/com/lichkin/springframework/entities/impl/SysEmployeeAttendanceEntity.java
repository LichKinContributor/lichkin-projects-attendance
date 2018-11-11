package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lichkin.framework.defines.annotations.ClassGenerator;
import com.lichkin.framework.defines.annotations.DefaultBooleanValue;
import com.lichkin.framework.defines.annotations.FieldGenerator;
import com.lichkin.framework.defines.annotations.InsertCheckType;
import com.lichkin.framework.defines.annotations.UpdateCheckType;
import com.lichkin.springframework.entities.suppers.BaseCompEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 员工考勤记录表实体类
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

		}

)
public class SysEmployeeAttendanceEntity extends BaseCompEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 40003L;

	/** 员工ID（SysEmployeeEntity.id） */
	@FieldGenerator()
	@Column(length = 64, nullable = false)
	private String loginId;

	/** 工作日期（yyyy-MM-dd） */
	@FieldGenerator(resultColumn = true)
	@Column(length = 10, nullable = false)
	private String workDate;

	/** 上班允许提前打卡时间（yyyy-MM-dd HH:mm:ss） */
	@FieldGenerator()
	@Column(length = 19)
	private String allowBeforeStartTime;

	/** 上班时间（yyyy-MM-dd HH:mm:ss） */
	@FieldGenerator(resultColumn = true)
	@Column(length = 19)
	private String startTime;

	/** 上班允许延迟打卡时间（yyyy-MM-dd HH:mm:ss） */
	@FieldGenerator()
	@Column(length = 19)
	private String allowAfterStartTime;

	/** 下班允许提前打卡时间（yyyy-MM-dd HH:mm:ss） */
	@FieldGenerator()
	@Column(length = 19)
	private String allowBeforeEndTime;

	/** 下班时间（yyyy-MM-dd HH:mm:ss） */
	@FieldGenerator(resultColumn = true)
	@Column(length = 19)
	private String endTime;

	/** 下班允许延迟打卡时间（yyyy-MM-dd HH:mm:ss） */
	@FieldGenerator()
	@Column(length = 19)
	private String allowAfterEndTime;

	/** 是否休息日（true:是;false:否.） */
	@DefaultBooleanValue
	@FieldGenerator(resultColumn = true)
	@Column
	private Boolean dayOff;

	/** 是否调休（true:是;false:否.） */
	@DefaultBooleanValue
	@FieldGenerator(resultColumn = true)
	@Column
	private Boolean takeWorkingDayOff;

	/** 是否请假（true:是;false:否.） */
	@DefaultBooleanValue
	@FieldGenerator(resultColumn = true)
	@Column
	private Boolean askForLeave;

	/** 是否旷工（true:是;false:否.） */
	@DefaultBooleanValue
	@FieldGenerator(resultColumn = true)
	@Column
	private Boolean absenteeism;

	/** 是否迟到（true:是;false:否.） */
	@DefaultBooleanValue
	@FieldGenerator(resultColumn = true)
	@Column
	private Boolean beLate;

	/** 是否早退（true:是;false:否.） */
	@DefaultBooleanValue
	@FieldGenerator(resultColumn = true)
	@Column
	private Boolean leaveEarly;

	/** 首次打卡时间（yyyy-MM-dd HH:mm:ss） */
	@FieldGenerator(resultColumn = true)
	@Column(length = 19)
	private String firstPunchTime;

	/** 末次打卡时间（yyyy-MM-dd HH:mm:ss） */
	@FieldGenerator(resultColumn = true)
	@Column(length = 19)
	private String lastPunchTime;

	/** 备注 */
	@FieldGenerator()
	@Column(length = 512)
	private String remarks;

}
