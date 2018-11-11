package com.lichkin.application.apis.api40001.U.n00;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysCompScheduleConfigBusService;
import com.lichkin.application.services.impl.SysEmployeeAttendanceService;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysEmployeeScheduleConfigR;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.springframework.entities.impl.SysCompScheduleConfigEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeScheduleConfigEntity;
import com.lichkin.springframework.services.LKApiBusUpdateService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("SysCompScheduleConfigU00Service")
public class S extends LKApiBusUpdateService<I, SysCompScheduleConfigEntity> {

	@Autowired
	private SysCompScheduleConfigBusService busService;


	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysCompScheduleConfig_EXIST(50000),

		;

		private final Integer code;

	}


	@Override
	protected boolean needCheckExist(I sin, String locale, String compId, String loginId, SysCompScheduleConfigEntity entity, String id) {
		if (!entity.getScheduleName().equals(sin.getScheduleName())) {
			return true;
		}
		return false;
	}


	@Override
	protected List<SysCompScheduleConfigEntity> findExist(I sin, String locale, String compId, String loginId, SysCompScheduleConfigEntity entity, String id) {
		return busService.findExist(id, compId, null, sin.getScheduleName());
	}


	@Override
	protected LKCodeEnum existErrorCode(I sin, String locale, String compId, String loginId) {
		return ErrorCodes.SysCompScheduleConfig_EXIST;
	}


	@Autowired
	private SysEmployeeAttendanceService employeeAttendanceService;


	@Override
	protected void afterSaveMain(I sin, String locale, String compId, String loginId, SysCompScheduleConfigEntity entity, String id) {
		QuerySQL sql = new QuerySQL(false, SysEmployeeScheduleConfigEntity.class);
		sql.eq(SysEmployeeScheduleConfigR.compId, entity.getCompId());
		sql.eq(SysEmployeeScheduleConfigR.scheduleId, entity.getId());
		List<SysEmployeeScheduleConfigEntity> list = dao.getList(sql, SysEmployeeScheduleConfigEntity.class);
		for (SysEmployeeScheduleConfigEntity e : list) {
			employeeAttendanceService.resetNextWeekEmployeeAttendance(e.getCompId(), e.getLoginId());
		}
	}

}
