package com.lichkin.application.services.bus.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysCompAttendanceAreaConfigR;
import com.lichkin.springframework.entities.impl.SysCompAttendanceAreaConfigEntity;
import com.lichkin.springframework.services.LKDBService;

@Service
public class SysCompAttendanceAreaConfigBusService extends LKDBService {

	public List<SysCompAttendanceAreaConfigEntity> findExist(String compId, String busCompId) {
		QuerySQL sql = new QuerySQL(false, SysCompAttendanceAreaConfigEntity.class);
		addConditionCompId(false, sql, SysCompAttendanceAreaConfigR.compId, compId, busCompId);
		return dao.getList(sql, SysCompAttendanceAreaConfigEntity.class);
	}

}
