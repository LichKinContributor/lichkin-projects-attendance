package com.lichkin.application.services.impl.quartz;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.lichkin.application.services.impl.SysEmployeeAttendanceService;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysCompR;
import com.lichkin.framework.defines.enums.impl.LKDateTimeTypeEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.springboot.services.LKBaseJobService;
import com.lichkin.framework.utils.LKDateTimeUtils;
import com.lichkin.springframework.entities.impl.SysCompEntity;
import com.lichkin.springframework.entities.impl.SysConfigQuartzEntity;
import com.lichkin.springframework.services.impl.SysConfigQuartzBusService;

/**
 * 处理公司所有员工的考勤信息
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
public class HandleCompAttendanceJobService extends LKBaseJobService {

	/** 日志对象 */
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private SysEmployeeAttendanceService service;

	@Autowired
	private SysConfigQuartzBusService quartzBusService;


	@Override
	protected void doTask() {
		// 查询任务信息
		SysConfigQuartzEntity configQuartz = quartzBusService.getOneJob(getClass().getSimpleName());
		DateTime yesterday = null;
		if (StringUtils.isNotBlank(configQuartz.getLastExecutionTime())) {
			yesterday = LKDateTimeUtils.toDateTime(configQuartz.getLastExecutionTime());
		} else {
			yesterday = new DateTime().minusDays(1);
		}
		configQuartz.setLastExecutionTime(LKDateTimeUtils.now());

		QuerySQL sql = new QuerySQL(SysCompEntity.class);
		sql.eq(SysCompR.usingStatus, LKUsingStatusEnum.USING);
		List<SysCompEntity> list = dao.getList(sql, SysCompEntity.class);
		if (CollectionUtils.isNotEmpty(list)) {
			// 今天处理昨天的信息
			for (SysCompEntity comp : list) {
				service.handCompAttendanceByDay(comp.getId(), LKDateTimeUtils.toString(yesterday, LKDateTimeTypeEnum.DATE_ONLY));
			}
		}

		// 任务执行完成后 修改最后一次执行时间
		quartzBusService.updateJob(configQuartz);
	}

}
