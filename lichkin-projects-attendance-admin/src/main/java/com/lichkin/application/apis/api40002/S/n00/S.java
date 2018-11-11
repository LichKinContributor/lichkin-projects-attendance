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
import com.lichkin.framework.defines.enums.impl.ScheduleTypeEnum;
import com.lichkin.springframework.entities.impl.SysCompScheduleConfigEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeScheduleConfigEntity;
import com.lichkin.springframework.services.LKApiBusGetListService;

@Service("SysEmployeeScheduleConfigS00Service")
public class S extends LKApiBusGetListService<I, O, SysEmployeeEntity> {

	@Override
	protected void initSQL(I sin, String locale, String compId, String loginId, QuerySQL sql) {
		sql.select(SysEmployeeR.id);
		sql.select(SysEmployeeR.userName);
		sql.select(SysEmployeeScheduleConfigR.id, "employeeAttendanceId");
		sql.select(SysCompScheduleConfigR.scheduleType);
		sql.select(SysCompScheduleConfigR.scheduleName);
		sql.select(SysCompScheduleConfigR.remarks);

		addConditionCompId(false, sql, SysEmployeeR.compId, compId, sin.getCompId());

		sql.leftJoin(SysEmployeeScheduleConfigEntity.class, new Condition(SysEmployeeR.id, SysEmployeeScheduleConfigR.loginId));
		sql.leftJoin(SysCompScheduleConfigEntity.class, new Condition(SysEmployeeScheduleConfigR.scheduleId, SysCompScheduleConfigR.id));

		String userName = sin.getUserName();
		if (StringUtils.isNotBlank(userName)) {
			sql.like(SysEmployeeR.userName, LikeType.ALL, userName);
		}

		ScheduleTypeEnum scheduleType = sin.getScheduleType();
		if (scheduleType != null) {
			sql.eq(SysCompScheduleConfigR.scheduleType, scheduleType);
		}

		sql.addOrders(new Order(SysEmployeeR.userName));
	}

}
