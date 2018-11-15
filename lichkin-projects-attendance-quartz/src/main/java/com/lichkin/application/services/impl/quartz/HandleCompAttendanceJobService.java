package com.lichkin.application.services.impl.quartz;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.lichkin.application.services.impl.SysEmployeeAttendanceService;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysCompR;
import com.lichkin.framework.defines.enums.impl.LKDateTimeTypeEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.springboot.services.LKBaseDBJobService;
import com.lichkin.framework.utils.LKDateTimeUtils;
import com.lichkin.springframework.entities.impl.SysCompEntity;

/**
 * 处理公司所有员工的考勤信息
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
public class HandleCompAttendanceJobService extends LKBaseDBJobService {

	@Autowired
	private SysEmployeeAttendanceService service;


	@Override
	protected void doTask(DateTime lastExecuteTime, DateTime lastFinishedTime) {
		// 忽略上线延迟导致的数据问题。
		// 本任务延迟执行两天处理考勤，以确保考勤处理正确。
		// 首次执行任务，处理前天的考勤。
		// 后续执行任务，顺延一天，故取上次执行时间的前一天。
		DateTime day = lastFinishedTime == null ? DateTime.now().minusDays(2) : lastFinishedTime.minusDays(1);

		QuerySQL sql = new QuerySQL(SysCompEntity.class);
		sql.eq(SysCompR.usingStatus, LKUsingStatusEnum.USING);
		List<SysCompEntity> list = dao.getList(sql, SysCompEntity.class);
		for (SysCompEntity comp : list) {
			service.handCompAttendanceByDay(comp.getId(), LKDateTimeUtils.toString(day, LKDateTimeTypeEnum.DATE_ONLY));
		}
	}

}
