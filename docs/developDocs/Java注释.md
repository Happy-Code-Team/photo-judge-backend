## 在注释中加入代码
`@code` 和 `<pre>` 标签结合：

将 `@code` 块嵌套在 `<pre>` 标签内。`@code` 确保了代码块内的文本会被视为代码（例如，不会对 <、> 进行 HTML 解析），而 `<pre>` 
标签则可以保留格式（如缩进和换行），这对于显示 JSON 结构非常有用。比如下面的写法。
```java
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
```