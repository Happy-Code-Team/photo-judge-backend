package com.photo.judge.controller.schedule;

import com.photo.judge.common.annotation.Desc;
import com.photo.judge.common.constants.ScheduleConstants;
import com.photo.judge.model.entity.quartz.QuartzJob;
import com.photo.judge.schedule.profile.ScheduleUtils;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {
	private final Scheduler scheduler;

	public ScheduleController(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	/**
	 * 新增或更新定时任务。
	 * 根据传入的 Job 配置对象，调度或修改 Quartz 任务。
	 *
	 * 参考输入 JSON (请求体示例):
	 * <pre>{@code
	 * {
	 * "concurrent": "1",          // 是否允许任务并发执行 (0:不允许, 1:允许)
	 * "cronExpression": "0/10 * * * * ?", // Cron 表达式，定义任务执行周期 (例如: "0/10 * * * * ?" 表示每10秒执行一次)
	 * "invokeTarget": "testTask.execute()", // 任务调用的目标字符串 (Spring Bean名称.方法名())
	 * "jobGroup": "mysqlGroup",   // 任务所属的组名
	 * "jobId": 9,                 // 任务ID (数据库主键，用于标识任务)
	 * "jobName": "新增 mysqlJob 任务", // 任务的名称
	 * "misfirePolicy": "1",       // 错失执行策略 (0:立即执行, 1:忽略, 2:重复执行)
	 * "remark": "",               // 任务备注信息
	 * "status": "0"               // 任务状态 (0:正常, 1:暂停)
	 * }
	 * }</pre>
	 *
	 * @param job 包含任务配置信息的 Job 实体对象 (例如 JobDto 或 JobConfig)
	 * @throws Exception 如果任务调度或保存过程中发生错误
	 */
	@Desc("增加定时任务")
	@PostMapping("/schedule/insert")
	public void insertQuartzJob(@RequestBody QuartzJob job) throws Exception {
		ScheduleUtils.createScheduleJob(scheduler, job);
	}
}
