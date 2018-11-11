package com.lichkin.application.apis.api40001.L.n00;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.application.utils.LKDictUtils;
import com.lichkin.application.utils.LKDictUtils4Attendance;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysCompScheduleConfigR;
import com.lichkin.framework.db.enums.LikeType;
import com.lichkin.framework.defines.enums.impl.ScheduleTypeEnum;
import com.lichkin.springframework.entities.impl.SysCompScheduleConfigEntity;
import com.lichkin.springframework.services.LKApiBusGetListService;

@Service("SysCompScheduleConfigL00Service")
public class S extends LKApiBusGetListService<I, O, SysCompScheduleConfigEntity> {

	@Override
	protected void initSQL(I sin, String locale, String compId, String loginId, QuerySQL sql) {
		// 主表
		sql.select(SysCompScheduleConfigR.id);
		sql.select(SysCompScheduleConfigR.insertTime);
		sql.select(SysCompScheduleConfigR.scheduleName);
		sql.select(SysCompScheduleConfigR.startTime0);
		sql.select(SysCompScheduleConfigR.endTime0);
		sql.select(SysCompScheduleConfigR.startTime1);
		sql.select(SysCompScheduleConfigR.endTime1);
		sql.select(SysCompScheduleConfigR.startTime2);
		sql.select(SysCompScheduleConfigR.endTime2);
		sql.select(SysCompScheduleConfigR.allowBeforeStartTimeMinutes);
		sql.select(SysCompScheduleConfigR.allowAfterStartTimeMinutes);
		sql.select(SysCompScheduleConfigR.allowBeforeEndTimeMinutes);
		sql.select(SysCompScheduleConfigR.allowAfterEndTimeMinutes);
		sql.select(SysCompScheduleConfigR.remarks);

		// 关联表

		// 字典表
		int i = 0;
		LKDictUtils.usingStatus(sql, SysCompScheduleConfigR.usingStatus, i++);
		LKDictUtils4Attendance.scheduleType(sql, SysCompScheduleConfigR.scheduleType, i++);

		// 筛选条件（必填项）
		// 公司ID
		addConditionCompId(false, sql, SysCompScheduleConfigR.compId, compId, sin.getCompId());
		// 在用状态
		addConditionUsingStatus(sql, SysCompScheduleConfigR.usingStatus, compId, sin.getUsingStatus());

		// 筛选条件（业务项）
		ScheduleTypeEnum scheduleType = sin.getScheduleType();
		if (scheduleType != null) {
			sql.eq(SysCompScheduleConfigR.scheduleType, scheduleType);
		}

		String scheduleName = sin.getScheduleName();
		if (StringUtils.isNotBlank(scheduleName)) {
			sql.like(SysCompScheduleConfigR.scheduleName, LikeType.ALL, scheduleName);
		}

		// 排序条件
		sql.addOrders(new Order(SysCompScheduleConfigR.id, false));
	}

}
