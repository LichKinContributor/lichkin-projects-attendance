package com.lichkin.application.apis.api40004.P.n00;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.application.utils.LKDictUtils;
import com.lichkin.application.utils.LKDictUtils4App;
import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysDictionaryR;
import com.lichkin.framework.db.beans.SysEmployeePunchTheClockR;
import com.lichkin.framework.db.beans.SysEmployeeR;
import com.lichkin.framework.db.enums.LikeType;
import com.lichkin.framework.defines.enums.impl.LKClientTypeEnum;
import com.lichkin.framework.defines.enums.impl.LKDateTimeTypeEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.utils.LKDateTimeUtils;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysEmployeeEntity;
import com.lichkin.springframework.entities.impl.SysEmployeePunchTheClockEntity;
import com.lichkin.springframework.services.LKApiBusGetPageService;

@Service("SysEmployeePunchTheClockP00Service")
public class S extends LKApiBusGetPageService<I, O, SysEmployeePunchTheClockEntity> {

	@Override
	protected void initSQL(I sin, ApiKeyValues<I> params, QuerySQL sql) {
		// 主表
		sql.select(SysEmployeePunchTheClockR.id);
		sql.select(SysEmployeePunchTheClockR.insertTime);
		sql.select(SysEmployeePunchTheClockR.versionX);
		sql.select(SysEmployeePunchTheClockR.versionY);
		sql.select(SysEmployeePunchTheClockR.versionZ);
		sql.select(SysEmployeePunchTheClockR.latitude);
		sql.select(SysEmployeePunchTheClockR.longitude);
		sql.select(SysEmployeePunchTheClockR.altitude);
		sql.select(SysEmployeePunchTheClockR.address);

		// 关联表
		sql.innerJoin(SysEmployeeEntity.class, new Condition(SysEmployeeR.id, SysEmployeePunchTheClockR.userId));
		sql.select(SysEmployeeR.userName);
		sql.select(SysEmployeeR.cellphone);

		// 字典表
		int i = 0;
		LKDictUtils.usingStatus(sql, SysEmployeePunchTheClockR.usingStatus, i++);
		LKDictUtils4App.appKey(sql, params.getCompId(), SysEmployeePunchTheClockR.appKey, i++);
		LKDictUtils.clientType(sql, SysEmployeePunchTheClockR.clientType, i++);

		// 筛选条件（必填项）
//		addConditionId(sql, SysEmployeeR.id, params.getId());
//		addConditionLocale(sql, SysEmployeeR.locale, params.getLocale());
		addConditionCompId(true, sql, SysEmployeeR.compId, params.getCompId(), params.getBusCompId());
		addConditionUsingStatus(true, params.getCompId(), sql, SysEmployeeR.usingStatus, params.getUsingStatus(), LKUsingStatusEnum.USING);

		// 筛选条件（业务项）
		String appKey = sin.getAppKey();
		if (StringUtils.isNotBlank(appKey)) {
			sql.like(SysDictionaryR.dictName, LikeType.ALL, appKey);
		}

		LKClientTypeEnum clientType = sin.getClientType();
		if (clientType != null) {
			sql.eq(SysEmployeePunchTheClockR.clientType, clientType);
		}

		String userName = sin.getUserName();
		if (StringUtils.isNotBlank(userName)) {
			sql.like(SysEmployeeR.userName, LikeType.ALL, userName);
		}

		String cellphone = sin.getCellphone();
		if (StringUtils.isNotBlank(cellphone)) {
			sql.like(SysEmployeeR.cellphone, LikeType.RIGHT, cellphone);
		}

		String startDate = sin.getStartDate();
		if (StringUtils.isNotBlank(startDate)) {
			sql.gte(SysEmployeePunchTheClockR.insertTime, LKDateTimeUtils.toString(LKDateTimeUtils.toDateTime(startDate, LKDateTimeTypeEnum.DATE_ONLY), LKDateTimeTypeEnum.TIMESTAMP_MIN));
		}

		String endDate = sin.getEndDate();
		if (StringUtils.isNotBlank(endDate)) {
			sql.lt(SysEmployeePunchTheClockR.insertTime, LKDateTimeUtils.toString(LKDateTimeUtils.toDateTime(endDate, LKDateTimeTypeEnum.DATE_ONLY).plusDays(1), LKDateTimeTypeEnum.TIMESTAMP_MIN));
		}

		// 排序条件
		sql.addOrders(new Order(SysEmployeeR.userName), new Order(SysEmployeePunchTheClockR.insertTime, false));
	}

}
