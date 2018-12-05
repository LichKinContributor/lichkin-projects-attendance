package com.lichkin.application.apis.api40005.S.n01;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysCompAttendanceAreaConfigR;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.utils.LKBeanUtils;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysCompAttendanceAreaConfigEntity;
import com.lichkin.springframework.services.LKApiVoidService;
import com.lichkin.springframework.services.LKDBService;

@Service("SysCompAttendanceAreaConfigS01Service")
public class S extends LKDBService implements LKApiVoidService<I> {

	@Override
	@Transactional
	public void handle(I cin, ApiKeyValues<I> params) throws LKException {
		QuerySQL sql = new QuerySQL(SysCompAttendanceAreaConfigEntity.class);
		sql.eq(SysCompAttendanceAreaConfigR.compId, params.getCompId());
		SysCompAttendanceAreaConfigEntity exist = dao.getOne(sql, SysCompAttendanceAreaConfigEntity.class);
		SysCompAttendanceAreaConfigEntity entity = LKBeanUtils.newInstance(true, cin, SysCompAttendanceAreaConfigEntity.class);
		entity.setCompId(params.getCompId());
		if (exist == null) {
			dao.persistOne(entity);
		} else {
			BeanUtils.copyProperties(entity, exist, "id");
			dao.mergeOne(exist);
		}
	}

}
