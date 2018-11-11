package com.lichkin.application.apis.api40001.LD.n00;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysCompScheduleConfigR;
import com.lichkin.framework.defines.beans.impl.LKDroplistBean;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.springframework.entities.impl.SysCompScheduleConfigEntity;
import com.lichkin.springframework.services.LKApiBusGetDroplistService;

@Service("SysCompScheduleConfigLD00Service")
public class S extends LKApiBusGetDroplistService<I> {

	@Override
	public List<LKDroplistBean> handle(I sin, String locale, String compId, String loginId) throws LKException {
		QuerySQL sql = new QuerySQL(SysCompScheduleConfigEntity.class);

		sql.select(SysCompScheduleConfigR.id, "value");
		sql.select(SysCompScheduleConfigR.scheduleName, "text");

		sql.eq(SysCompScheduleConfigR.usingStatus, LKUsingStatusEnum.USING);
		sql.eq(SysCompScheduleConfigR.compId, compId);
		sql.eq(SysCompScheduleConfigR.scheduleType, sin.getScheduleType());

		return dao.getList(sql, LKDroplistBean.class);
	}

}
