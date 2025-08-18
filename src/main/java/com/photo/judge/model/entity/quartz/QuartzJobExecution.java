package com.photo.judge.model.entity.quartz;

import com.photo.judge.schedule.profile.JobInvokeUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 * 定时任务处理（允许并发执行）
 * 该类用于执行QuartzJob任务，允许多个实例并发执行。
 */
public class QuartzJobExecution extends AbstractQuartzJob {
	/**
	 * 执行方法
	 * @param context 工作执行上下文对象
	 * @throws JobExecutionException 执行过程中的异常
	 */
	@Override
	protected void doExecute(JobExecutionContext context, QuartzJob job) throws Exception {
		JobInvokeUtil.invokeMethod(job);
	}
}
