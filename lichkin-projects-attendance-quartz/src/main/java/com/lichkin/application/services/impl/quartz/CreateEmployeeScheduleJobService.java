package com.lichkin.application.services.impl.quartz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysCompR;
import com.lichkin.framework.defines.LKConfigStatics;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.springboot.configurations.LKQuartzManager;
import com.lichkin.framework.springboot.services.LKBaseDBJobService;
import com.lichkin.springframework.entities.impl.SysCompEntity;

/**
 * 生成员工排班信息
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
public class CreateEmployeeScheduleJobService extends LKBaseDBJobService {

	/** 日志对象 */
	protected final Log logger = LogFactory.getLog(getClass());


	@Override
	protected void doTask(DateTime lastExecuteTime, DateTime lastFinishedTime) {
		QuerySQL sql = new QuerySQL(SysCompEntity.class);
		sql.eq(SysCompR.usingStatus, LKUsingStatusEnum.USING);
		List<SysCompEntity> list = dao.getList(sql, SysCompEntity.class);
		if (CollectionUtils.isNotEmpty(list)) {
			LKQuartzManager manager = LKQuartzManager.getInstance();
			for (SysCompEntity comp : list) {
				Map<String, Object> jobParams = new HashMap<>();
				jobParams.put("compId", comp.getId());
				if (!manager.checkExistSecheduleJob(LKConfigStatics.SYSTEM_NAME, "CreateCompEmployeeScheduleJobService" + comp.getId())) {
					manager.scheduleJob(false, LKConfigStatics.SYSTEM_TAG, "CreateCompEmployeeScheduleJobService" + comp.getId(), "com.lichkin.application.services.impl.quartz.CreateCompEmployeeScheduleJobService", "executeTask", "0 1 5 ? * MON", jobParams);
				}
			}
			manager.start();
		}
	}

}
