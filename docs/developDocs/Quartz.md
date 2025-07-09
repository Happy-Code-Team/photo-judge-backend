[参考文件1](https://zhuanlan.zhihu.com/p/522284183)
[参考文件2](https://www.cnblogs.com/summerday152/p/14192845.html)

## 注意：
上面的<参考文件1>中有一个 `ScheduleConfig`, 其中的 `prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");` 
在升级后，变成了 `prop.put("org.quartz.jobStore.class", "org.springframework.scheduling.quartz.LocalDataSourceJobStore");` 。