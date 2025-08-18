package com.photo.judge.model.entity.quartz;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.photo.judge.common.annotation.Desc;
import com.photo.judge.common.constants.ScheduleConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("quartz_job")
@Desc("定时任务表")
@Accessors(chain = true)//开启链式
public class QuartzJob implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Desc(value = "任务序号")
	@TableId
	private Long jobId;

	@Desc(value = "任务名称")
	private String jobName;

	@Desc(value = "任务组名")
	private String jobGroup;

	@Desc(value = "调用目标字符串: beanName.methodName()")
	private String invokeTarget;

	@Desc(value = "执行表达式")
	private String cronExpression;

	@Desc(value = "cron计划策略, 0=默认,1=立即触发执行,2=触发一次执行,3=不触发立即执行")
	private String misfirePolicy = ScheduleConstants.MISFIRE_DEFAULT;

	@Desc(value = "并发执行, 0=允许,1=禁止")
	private String concurrent;

	@Desc(value = "任务状态（0正常 1暂停）")
	private String status;

	@Desc(value = "备注")
	private String remark;

}
