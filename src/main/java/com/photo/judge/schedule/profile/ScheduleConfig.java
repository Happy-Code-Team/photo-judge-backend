package com.photo.judge.schedule.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class ScheduleConfig {
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) {
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setDataSource(dataSource);

		// quartz参数
		Properties prop = new Properties();
		// 调度器是实例子
		prop.put("org.quartz.scheduler.instanceName", "photo-judge-schedule");
		// 调度器实例的 id
		prop.put("org.quartz.scheduler.instanceId", "AUTO");
		// 线程池配置
		prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
		// 最大可以并发的任务
		prop.put("org.quartz.threadPool.threadCount", "10");
		// 设置线程的优先级
		prop.put("org.quartz.threadPool.threadPriority", "5");
		// JobStore配置, 设置持久化类型为 JDBC，持久化到数据库中
		prop.put("org.quartz.jobStore.class", "org.springframework.scheduling.quartz.LocalDataSourceJobStore");
		// 集群配置
//		prop.put("org.quartz.jobStore.isClustered", "false");
//		prop.put("org.quartz.jobStore.clusterCheckinInterval", "15000");
//		prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "1");
//		prop.put("org.quartz.jobStore.txIsolationLevelSerializable", "true");

		// prop.put("org.quartz.jobStore.selectWithLockSQL", "SELECT * FROM {0}LOCKS UPDLOCK WHERE LOCK_NAME = ?");
		// 设置任务错过触发的最大容忍时间, 单位是毫秒，12 秒则认为达到最大值
		prop.put("org.quartz.jobStore.misfireThreshold", "12000");
		// 启动串行事务隔离级别
		prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
		factory.setQuartzProperties(prop);

		factory.setSchedulerName("photo-judge-schedule");
		// 延时启动
		factory.setStartupDelay(1);
		// 指定 Spring 应用上下文在 Quartz 上下文中的键名。后续可通过 JobExecutionContext 获取 Spring Bean。
		factory.setApplicationContextSchedulerContextKey("applicationContextKey");
		// 启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
		factory.setOverwriteExistingJobs(true);
		// 设置自动启动，默认为true
		factory.setAutoStartup(true);

		return factory;
	}
}
