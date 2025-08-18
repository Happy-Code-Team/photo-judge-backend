package com.photo.judge.common.utils;

import cn.hutool.core.lang.Snowflake;

public class SnowflakeUtils {
	// 这个要判断怎么和机器码关联起来，这里我们简答的不做处理
	private static final Snowflake snowflake = new Snowflake(1, 1);

	/**
	 * 生成一个全局唯一的 ID
	 * @return 唯一 ID
	 */
	public static String generateId() {
		return String.valueOf(snowflake.nextId());
	}
}
