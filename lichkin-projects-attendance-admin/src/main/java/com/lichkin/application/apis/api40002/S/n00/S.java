package com.lichkin.application.apis.api40002.S.n00;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysCompScheduleConfigR;
import com.lichkin.framework.db.beans.SysEmployeeR;
import com.lichkin.framework.db.beans.SysEmployeeScheduleConfigR;
import com.lichkin.framework.db.enums.LikeType;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.enums.impl.ScheduleTypeEnum;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysCompScheduleConfigEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeScheduleConfigEntity;
import com.lichkin.springframework.services.LKApiBusGetListService;

@Service("SysEmployeeScheduleConfigS00Service")
public class S extends LKApiBusGetListService<I, O, SysEmployeeEntity> {

	@Override
	protected void initSQL(I sin, ApiKeyValues<I> params, QuerySQL sql) {
		// 主表
		sql.select(SysEmployeeR.id);
		sql.select(SysEmployeeR.userName);
		sql.select(SysEmployeeScheduleConfigR.id, "employeeAttendanceId");
		sql.select(SysCompScheduleConfigR.scheduleType);
		sql.select(SysCompScheduleConfigR.scheduleName);
		sql.select(SysCompScheduleConfigR.remarks);

		// 关联表
		sql.leftJoin(SysEmployeeScheduleConfigEntity.class, new Condition(SysEmployeeR.id, SysEmployeeScheduleConfigR.employeeId));
		sql.leftJoin(SysCompScheduleConfigEntity.class, new Condition(SysEmployeeScheduleConfigR.scheduleId, SysCompScheduleConfigR.id));

		// 字典表
//		int i = 0;

		// 筛选条件（必填项）
//		addConditionId(sql, SysEmployeeR.id, params.getId());
//		addConditionLocale(sql, SysEmployeeR.locale, params.getLocale());
		addConditionCompId(true, sql, SysEmployeeR.compId, params.getCompId(), params.getBusCompId());
		addConditionUsingStatus(true, params.getCompId(), sql, SysEmployeeR.usingStatus, params.getUsingStatus(), LKUsingStatusEnum.USING);

		// 筛选条件（业务项）
		String userName = sin.getUserName();
		if (StringUtils.isNotBlank(userName)) {
			sql.like(SysEmployeeR.userName, LikeType.ALL, userName);
		}

		ScheduleTypeEnum scheduleType = sin.getScheduleType();
		if (scheduleType != null) {
			sql.eq(SysCompScheduleConfigR.scheduleType, scheduleType);
		}

		// 排序条件
		sql.addOrders(new Order(SysEmployeeR.userName));
	}

}
