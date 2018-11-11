package com.lichkin.application.utils;

import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.defines.LKFrameworkStatics;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 字典工具类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LKDictUtils4Attendance extends LKDictUtils {

	/**
	 * 连接字典表（排班类型）
	 * @param sql SQL语句对象
	 * @param columnResId 列资源ID
	 * @param tableIdx 字典表序号（从0开始）
	 */
	public static void scheduleType(QuerySQL sql, int columnResId, int tableIdx) {
		leftJoinDictionary(sql, "scheduleType", LKFrameworkStatics.LichKin, "SCHEDULE_TYPE", columnResId, tableIdx);
	}

}
