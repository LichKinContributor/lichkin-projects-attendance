package com.lichkin.application.apis.api40001.LD.n01;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.defines.beans.impl.LKDroplistBean;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.utils.i18n.LKI18NUtils;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysCompScheduleConfigEntity;
import com.lichkin.springframework.services.LKApiBusGetDroplistService;

@Service("SysCompScheduleConfigLD01Service")
public class S extends LKApiBusGetDroplistService<I> {

	@Override
	public List<LKDroplistBean> handle(I sin, ApiKeyValues<I> params) throws LKException {
		SysCompScheduleConfigEntity entity = dao.findOneById(SysCompScheduleConfigEntity.class, sin.getScheduleId());

		List<LKDroplistBean> list = new ArrayList<>();

		list.add(new LKDroplistBean(LKI18NUtils.getString(LKI18NUtils.getLocale(params.getLocale()), "app-attendance-api-extends", "com.lichkin.api.dayOff"), -1));

		if (StringUtils.isNotBlank(entity.getStartTime0())) {
			list.add(new LKDroplistBean(entity.getStartTime0() + "~" + entity.getEndTime0(), 0));
		}

		if (StringUtils.isNotBlank(entity.getStartTime1())) {
			list.add(new LKDroplistBean(entity.getStartTime1() + "~" + entity.getEndTime1(), 1));
		}

		if (StringUtils.isNotBlank(entity.getStartTime2())) {
			list.add(new LKDroplistBean(entity.getStartTime2() + "~" + entity.getEndTime2(), 2));
		}

		return list;
	}

}
