package com.lichkin.application.apis.api40002.S.n01;

import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysCompScheduleConfigR;
import com.lichkin.framework.db.beans.SysEmployeeR;
import com.lichkin.framework.db.beans.SysEmployeeScheduleConfigR;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysCompScheduleConfigEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeScheduleConfigEntity;
import com.lichkin.springframework.services.LKApiService;
import com.lichkin.springframework.services.LKDBService;

@Service("SysEmployeeScheduleConfigS01Service")
public class S extends LKDBService implements LKApiService<I, SO> {

	@Override
	public SO handle(I sin, ApiKeyValues<I> params) throws LKException {
		QuerySQL sql = new QuerySQL(SysEmployeeEntity.class);

		sql.selectTable(SysCompScheduleConfigEntity.class);
		sql.select(SysEmployeeR.userName);
		sql.select(SysEmployeeScheduleConfigR.scheduleInfo);

//		addConditionId(sql, SysEmployeeR.id, params.getId());
//		addConditionLocale(sql, SysEmployeeR.locale, params.getLocale());
		addConditionCompId(true, sql, SysEmployeeR.compId, params.getCompId(), params.getBusCompId());
		addConditionUsingStatus(true, params.getCompId(), sql, SysEmployeeR.usingStatus, params.getUsingStatus(), LKUsingStatusEnum.USING);

		sql.leftJoin(SysEmployeeScheduleConfigEntity.class, new Condition(SysEmployeeR.id, SysEmployeeScheduleConfigR.employeeId));
		sql.leftJoin(SysCompScheduleConfigEntity.class, new Condition(SysEmployeeScheduleConfigR.scheduleId, SysCompScheduleConfigR.id));

		sql.eq(SysEmployeeR.id, sin.getId());

		return dao.getOne(sql, SO.class);
	}

}
