package com.photo.judge.model.entity.quartz;

import com.photo.judge.schedule.profile.JobInvokeUtil;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（禁止并发执行）
 * 该类用于执行QuartzJob任务，禁止多个实例并发执行。
 */
@DisallowConcurrentExecution
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob {
	@Override
	protected void doExecute(JobExecutionContext context, QuartzJob job) throws Exception {
		JobInvokeUtil.invokeMethod(job);
	}
}
