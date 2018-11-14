package com.lichkin.application.services.impl.quartz;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.lichkin.application.services.impl.SysEmployeeAttendanceService;
import com.lichkin.defines.AttendanceStatics;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysActivitiFormDataR;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.enums.impl.ApprovalStatusEnum;
import com.lichkin.framework.defines.enums.impl.LKDateTimeTypeEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.springboot.services.LKBaseJobService;
import com.lichkin.framework.utils.LKDateTimeUtils;
import com.lichkin.springframework.entities.impl.SysActivitiFormDataEntity;
import com.lichkin.springframework.entities.impl.SysConfigQuartzEntity;
import com.lichkin.springframework.services.impl.SysConfigQuartzBusService;

/**
 * 处理员工请假考勤信息
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
public class HandleEmployeeLeaveAndRestJobService extends LKBaseJobService {

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
		DateTime now = new DateTime();
		DateTime yesterday = now.minusDays(1);
		if (StringUtils.isNotBlank(configQuartz.getLastExecutionTime())) {
			yesterday = LKDateTimeUtils.toDateTime(configQuartz.getLastExecutionTime());
		}
		configQuartz.setLastExecutionTime(LKDateTimeUtils.now());

		// 查询已经审核通过的请假信息
		QuerySQL sql = new QuerySQL(SysActivitiFormDataEntity.class);
		sql.eq(SysActivitiFormDataR.usingStatus, LKUsingStatusEnum.USING);
		sql.eq(SysActivitiFormDataR.approvalStatus, ApprovalStatusEnum.APPROVED);
		sql.in(SysActivitiFormDataR.processCode, AttendanceStatics.LEAVE + LKFrameworkStatics.SPLITOR + AttendanceStatics.REST);
		sql.gte(SysActivitiFormDataR.approvalTime, LKDateTimeUtils.toString(yesterday, LKDateTimeTypeEnum.DATE_ONLY_MIN) + "000000000");
		sql.lt(SysActivitiFormDataR.approvalTime, LKDateTimeUtils.now());
		List<SysActivitiFormDataEntity> list = dao.getList(sql, SysActivitiFormDataEntity.class);
		for (SysActivitiFormDataEntity formData : list) {
			service.handAttendanceLeaveAndRest(formData.getApproverLoginId(), formData.getCompId(), formData.getProcessCode(), formData.getField1(), formData.getField2());
		}

		// 任务执行完成后 修改最后一次执行时间
		quartzBusService.updateJob(configQuartz);
	}

}
