package com.lichkin.application.apis.api40005.S.n00;

import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysCompAttendanceAreaConfigR;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysCompAttendanceAreaConfigEntity;
import com.lichkin.springframework.services.LKApiService;
import com.lichkin.springframework.services.LKDBService;

@Service("SysCompAttendanceAreaConfigS00Service")
public class S extends LKDBService implements LKApiService<I, O> {

	@Override
	public O handle(I cin, ApiKeyValues<I> params) throws LKException {
		QuerySQL sql = new QuerySQL(SysCompAttendanceAreaConfigEntity.class);
		sql.eq(SysCompAttendanceAreaConfigR.compId, params.getCompId());
		return dao.getOne(sql, O.class);
	}

}
