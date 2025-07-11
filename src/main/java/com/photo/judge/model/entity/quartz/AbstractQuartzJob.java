package com.photo.judge.model.entity.quartz;

import cn.hutool.core.bean.BeanUtil;
import com.photo.judge.common.constants.ScheduleConstants;
import com.photo.judge.common.mapstucture.IMapStructCommonService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

@Slf4j
public abstract class AbstractQuartzJob implements Job {
	/**
	 * 线程本地变量
	 */
	private static final ThreadLocal<Date> threadLocal = new ThreadLocal<>();

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		QuartzJob job = new QuartzJob();
		IMapStructCommonService.MapStruct.clone((QuartzJob) context.getMergedJobDataMap().get(ScheduleConstants.TASK_PROPERTIES), job);
		try {
			before(context, job);
			doExecute(context, job);
			after(context, job, null);
		} catch (Exception e) {
			log.error("任务执行异常  - ：", e);
			after(context, job, e);
		}
	}

	/**
	 * 执行前, 钩子方法，由具体的子类进行实现
	 * @param context 工作执行上下文对象
	 * @param job     系统计划任务
	 */
	protected void before(JobExecutionContext context, QuartzJob job) {
		threadLocal.set(new Date());
	}

	/**
	 * 执行后
	 *
	 * @param context 工作执行上下文对象
	 * @param sysJob  系统计划任务
	 */
	protected void after(JobExecutionContext context, QuartzJob sysJob, Exception e) {

	}

	/**
	 * 执行方法，由子类重载
	 *
	 * @param context 工作执行上下文对象
	 * @param job     系统计划任务
	 * @throws Exception 执行过程中的异常
	 */
	protected abstract void doExecute(JobExecutionContext context, QuartzJob job) throws Exception;
}
