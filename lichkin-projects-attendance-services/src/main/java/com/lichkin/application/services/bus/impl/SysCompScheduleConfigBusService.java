package com.lichkin.application.services.bus.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysCompScheduleConfigR;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.springframework.entities.impl.SysCompScheduleConfigEntity;
import com.lichkin.springframework.services.LKDBService;

@Service
public class SysCompScheduleConfigBusService extends LKDBService {

	public List<SysCompScheduleConfigEntity> findExist(String id, String compId, String busCompId, String scheduleName) {
		QuerySQL sql = new QuerySQL(false, SysCompScheduleConfigEntity.class);

		if (StringUtils.isNotBlank(id)) {
			sql.neq(SysCompScheduleConfigR.id, id);
		}

		addConditionUsingStatus(sql, SysCompScheduleConfigR.usingStatus, busCompId, null, LKUsingStatusEnum.USING);
		addConditionCompId(true, sql, SysCompScheduleConfigR.compId, compId, busCompId);

		sql.eq(SysCompScheduleConfigR.scheduleName, scheduleName);

		return dao.getList(sql, SysCompScheduleConfigEntity.class);
	}

}
