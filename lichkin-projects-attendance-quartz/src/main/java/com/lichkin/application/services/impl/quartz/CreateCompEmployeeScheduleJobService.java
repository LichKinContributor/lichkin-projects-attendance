package com.lichkin.application.services.impl.quartz;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lichkin.application.services.impl.SysEmployeeAttendanceService;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysEmployeeR;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.springboot.services.LKBaseJobService;
import com.lichkin.springframework.entities.impl.SysEmployeeEntity;

/**
 * 生成单个公司员工排班信息
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
public class CreateCompEmployeeScheduleJobService extends LKBaseJobService {

	/** 日志对象 */
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private SysEmployeeAttendanceService service;


	@Override
	protected void doTask() {
		String compId = context.getJobDetail().getJobDataMap().get("compId").toString();
		QuerySQL sql = new QuerySQL(SysEmployeeEntity.class);
		sql.eq(SysEmployeeR.usingStatus, LKUsingStatusEnum.USING);
		sql.eq(SysEmployeeR.compId, compId);
		List<SysEmployeeEntity> list = dao.getList(sql, SysEmployeeEntity.class);
		for (SysEmployeeEntity employee : list) {
			service.resetNextWeekEmployeeAttendance(compId, employee.getId());
		}
	}

}
