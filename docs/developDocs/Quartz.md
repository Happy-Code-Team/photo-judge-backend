[参考文件1](https://zhuanlan.zhihu.com/p/522284183)
[参考文件2](https://www.cnblogs.com/summerday152/p/14192845.html)

## 注意：
上面的<参考文件1>中有一个 `ScheduleConfig`, 其中的 `prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");` 
在升级后，变成了 `prop.put("org.quartz.jobStore.class", "org.springframework.scheduling.quartz.LocalDataSourceJobStore");` 。

## Quartz 的几个核心的概念：
* Job: 任务，Quartz 中的任务是实现了 `org.quartz.Job` 接口的类。每个 Job 都有一个唯一的标识符（JobKey），可以通过这个标识符来调度和管理任务。
在当前的项目中，我们设置了一个 AbstractJob 类，这个类实现了 `org.quartz.Job` 接口，并实现了默认的 `execute()` 方法。我们在这个 `execute()` 方法中，
增加了 before 和 after 方法，可以在定时任务前后做一些操作。然后设置了两个具体的类，可以并发执行的类和不可以并发执行的类。
 
    可以看到标准的 `execute()` 会传入 JobExecutionContext, 这个就是我们从前端获取到的任务信息，可以放到这里，Scheduler 调度器会将这些信息传递给 Job 的 `execute()` 方法。

     ```java
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
    ```
* JobDetail: 任务详情，Quartz 中的 JobDetail 是一个描述任务的对象，它包含了任务的类名、任务参数等信息。JobDetail 是一个可序列化的对象，可以存储在数据库中。
而且我们前端传入的参数是放到 JobDetail 中的。JobDetail 是 Job 信息补充，Job 负责具体的执行逻辑。JobDetail 的 `JobDataMap` 可以存储任务的参数，
* Trigger: 触发器，Quartz 中的触发器是用来定义任务的执行时间和频率的。Quartz 支持多种类型的触发器，包括简单触发器（SimpleTrigger）和 Cron 表达式触发器（CronTrigger）。
  - SimpleTrigger: 简单触发器，用于定义任务在指定时间内执行一次或多次。
  - CronTrigger: Cron 表达式触发器，用于定义任务按照 Cron 表达式的规则执行。

    在当前项目中，我们使用了 `CronTrigger` 来定义任务的执行时间和频率。前端传入的 `cronExpression` 就是 Cron 表达式。
* scheduler: 调度器，当 SchedulerFactory 创建 Scheduler 以后，生命周期就开始了，直到调用 `shutdown()` 方法。一旦 `scheduler` 创建，
它就可以做任何 `scheduling`, 比如 添加、删除、暂停、恢复、查询任务等。列出所有的 jobs 和 triggers 等等。在目前的项目中，scheduler 是我们
通过 SchedulerFactory 创建的。 
    ```java
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
    ```
  
## 说明：
`Quartz` 执行定时任务，也是线程池来的。有一个调度器主线程和工作线程。

