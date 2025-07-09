package com.photo.judge.schedule.profile;

import com.photo.judge.common.constants.ScheduleConstants;
import com.photo.judge.model.entity.quartz.QuartzDisallowConcurrentExecution;
import com.photo.judge.model.entity.quartz.QuartzJob;
import com.photo.judge.model.entity.quartz.QuartzJobExecution;
import org.quartz.*;

public class ScheduleUtils {
	/**
	 * 得到quartz任务类, 允许并发执行或者不允许并发执行
	 *
	 * @param job 执行计划
	 * @return 具体执行任务类
	 */
	private static Class<? extends Job> getQuartzJobClass(QuartzJob job) {
		boolean isConcurrent = "0".equals(job.getConcurrent());
		return isConcurrent ? QuartzJobExecution.class : QuartzDisallowConcurrentExecution.class;
	}

	/**
	 * 创建了新的定时任务，应该在 quartz_job 中维护信息，供前端展示，每次修改和删除，都要修改这个表。
	 * TODO: 这个还没有做
	 * @param scheduler
	 * @param job
	 * @throws Exception
	 */
	public static void createScheduleJob(Scheduler scheduler, QuartzJob job) throws Exception {
		Class<? extends Job> jobClass = getQuartzJobClass(job);

		// 构建job信息
		Long jobId = job.getJobId();
		String jobGroup = job.getJobGroup();
		JobKey jobKey = getJobKey(jobId, jobGroup);
		JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobKey).build();

		// 表达式调度构建器
		CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
		cronScheduleBuilder = handleCronScheduleMisfirePolicy(job, cronScheduleBuilder);

		// 按新的cronExpression表达式构建一个新的trigger
		TriggerKey triggerKey = getTriggerKey(jobId, jobGroup);
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
			.withSchedule(cronScheduleBuilder).build();

		// 放入参数，在 AbstractQuartzJob
		jobDetail.getJobDataMap().put(ScheduleConstants.TASK_PROPERTIES, job);

		// 判断当前任务是否已经在调度器中存在，如果存在，删除
		if (scheduler.checkExists(jobKey)) {
			// 防止创建时存在数据问题 先移除，然后在执行创建操作
			scheduler.deleteJob(jobKey);
		}
		scheduler.scheduleJob(jobDetail, trigger);

		// 暂停任务, 新加入的任务如果设置状态为暂停，则调用 scheduler 暂停执行任务
		if (job.getStatus().equals(ScheduleConstants.Status.PAUSE.getValue())) {
			scheduler.pauseJob(ScheduleUtils.getJobKey(jobId, jobGroup));
			// scheduler 还提供了对应的 resumeJob 方法来恢复任务
		}

	}

	/**
	 * 构建任务键对象
	 */
	public static JobKey getJobKey(Long jobId, String jobGroup) {
		return JobKey.jobKey(ScheduleConstants.TASK_CLASS_NAME + jobId, jobGroup);
	}

	/**
	 * 构建任务触发对象
	 */
	public static TriggerKey getTriggerKey(Long jobId, String jobGroup) {
		return TriggerKey.triggerKey(ScheduleConstants.TASK_CLASS_NAME + jobId, jobGroup);
	}

	/**
	 * 设置定时任务策略
	 */
	public static CronScheduleBuilder handleCronScheduleMisfirePolicy(QuartzJob job, CronScheduleBuilder cb)
		throws Exception {
		return switch (job.getMisfirePolicy()) {
			case ScheduleConstants.MISFIRE_DEFAULT -> cb;
			case ScheduleConstants.MISFIRE_IGNORE_MISFIRES -> cb.withMisfireHandlingInstructionIgnoreMisfires();
			case ScheduleConstants.MISFIRE_FIRE_AND_PROCEED -> cb.withMisfireHandlingInstructionFireAndProceed();
			case ScheduleConstants.MISFIRE_DO_NOTHING -> cb.withMisfireHandlingInstructionDoNothing();
			default ->
				throw new Exception("The task misfire policy '" + job.getMisfirePolicy() + "' cannot be used in cron schedule tasks");
		};
	}
}
