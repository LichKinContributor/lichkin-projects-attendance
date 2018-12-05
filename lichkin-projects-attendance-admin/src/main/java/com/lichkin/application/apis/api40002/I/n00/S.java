package com.lichkin.application.apis.api40002.I.n00;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.application.services.bus.impl.SysEmployeeScheduleConfigBusService;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysEmployeeScheduleConfigR;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.utils.LKBeanUtils;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysEmployeeScheduleConfigEntity;
import com.lichkin.springframework.services.LKApiVoidService;
import com.lichkin.springframework.services.LKDBService;

@Service("SysEmployeeScheduleConfigI00Service")
public class S extends LKDBService implements LKApiVoidService<I> {

	@Autowired
	private SysEmployeeScheduleConfigBusService busService;


	@Override
	@Transactional
	public void handle(I cin, ApiKeyValues<I> params) throws LKException {
		QuerySQL sql = new QuerySQL(SysEmployeeScheduleConfigEntity.class);
		sql.eq(SysEmployeeScheduleConfigR.employeeId, cin.getEmployeeId());
		SysEmployeeScheduleConfigEntity exist = dao.getOne(sql, SysEmployeeScheduleConfigEntity.class);
		SysEmployeeScheduleConfigEntity entity = LKBeanUtils.newInstance(true, cin, SysEmployeeScheduleConfigEntity.class);
		entity.setConfigTime(busService.analysisConfigTime());
		entity.setCompId(params.getCompId());
		if (exist == null) {
			dao.persistOne(entity);
		} else {
			BeanUtils.copyProperties(entity, exist, "id");
			dao.mergeOne(exist);
		}
	}

}
