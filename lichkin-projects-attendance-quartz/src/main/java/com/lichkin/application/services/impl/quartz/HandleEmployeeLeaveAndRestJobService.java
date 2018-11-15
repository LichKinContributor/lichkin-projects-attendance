package com.lichkin.application.services.impl.quartz;

import java.util.List;

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
import com.lichkin.framework.springboot.services.LKBaseDBJobService;
import com.lichkin.framework.utils.LKDateTimeUtils;
import com.lichkin.springframework.entities.impl.SysActivitiFormDataEntity;

/**
 * 处理员工请假、调休考勤信息
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
public class HandleEmployeeLeaveAndRestJobService extends LKBaseDBJobService {

	@Autowired
	private SysEmployeeAttendanceService service;


	@Override
	protected void doTask(DateTime lastExecuteTime, DateTime lastFinishedTime) {
		// 忽略上线延迟导致的数据问题。
		// 本任务延迟执行两天处理考勤，以确保考勤处理正确。
		// 首次执行任务，处理前天的考勤。
		// 后续执行任务，顺延一天，故取上次执行时间的前一天。
		DateTime day = lastExecuteTime == null ? DateTime.now().minusDays(2) : lastExecuteTime.minusDays(1);

		// 查询已经审核通过的请假、调休信息
		QuerySQL sql = new QuerySQL(SysActivitiFormDataEntity.class);
		sql.eq(SysActivitiFormDataR.usingStatus, LKUsingStatusEnum.USING);
		sql.eq(SysActivitiFormDataR.approvalStatus, ApprovalStatusEnum.APPROVED);
		sql.in(SysActivitiFormDataR.processCode, AttendanceStatics.LEAVE + LKFrameworkStatics.SPLITOR + AttendanceStatics.REST);

		sql.gte(SysActivitiFormDataR.approvalTime, LKDateTimeUtils.toString(LKDateTimeUtils.toDateTime(LKDateTimeUtils.toString(day, LKDateTimeTypeEnum.DATE_ONLY), LKDateTimeTypeEnum.DATE_ONLY), LKDateTimeTypeEnum.TIMESTAMP_MIN));

		List<SysActivitiFormDataEntity> list = dao.getList(sql, SysActivitiFormDataEntity.class);
		for (SysActivitiFormDataEntity formData : list) {
			service.handAttendanceLeaveAndRest(formData.getApproverLoginId(), formData.getCompId(), formData.getProcessCode(), formData.getField1(), formData.getField2());
		}
	}

}
