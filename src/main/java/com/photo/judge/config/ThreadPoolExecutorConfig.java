package com.photo.judge.config;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * 详细信息见 docs/developDocs/线程池.md
 */
@Slf4j
@Configuration
public class ThreadPoolExecutorConfig {

	/**
	 * 系统的核心线程池，后序如果有其他的任务，可以继续创建线程池。
	 */
	@Bean("coreThreadPoolExecutor")
	public Executor coreThreadPoolExecutor() {
		// ThreadPoolTaskExecutor 是 Spring 提供的线程池执行器，封装了 ThreadPoolExecutor
		ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();

		int processNum = Runtime.getRuntime().availableProcessors(); // 返回可用处理器的Java虚拟机的数量
		int corePoolSize = (int) (processNum / (1 - 0.2));
		int maxPoolSize = (int) (processNum / (1 - 0.5));
		int queueCapacity = maxPoolSize * 10; // 队列长度
		int keepAliveSeconds = 300; // 线程空闲时间
		log.info("coreThreadPoolExecutor 线程池配置: corePoolSize={}, maxPoolSize={}, queueCapacity={}, keepAliveSeconds={}",
			corePoolSize, maxPoolSize, queueCapacity, keepAliveSeconds);

		/*
		 * - corePoolSize：线程池的核心线程数量，线程池会保留这些核心线程，即使它们处于空闲状态。
		 * - maximumPoolSize：线程池的最大线程数
		 * - keepAliveTime：当线程池中的线程数量超过核心线程数时，多余的线程在空闲状态下存活的最长时间。
		 * - unit：keepAliveTime的时间单位。
		 * - workQueue：任务队列，用来储存等待执行的任务。
		 * - RejectedExecutionHandler：拒绝策略, CallerRunsPolicy 调用线程自己执行任务的 Run 方法
		 */
		threadPoolExecutor.setCorePoolSize(corePoolSize); // 核心池大小
		threadPoolExecutor.setMaxPoolSize(maxPoolSize); // 最大线程数
		// ThreadPoolTaskExecutor 默认使用 LinkedBlockingQueue 作为阻塞队列
		threadPoolExecutor.setQueueCapacity(maxPoolSize); // 队列长度
		// 设置线程的优先级，优先级高的线程获得 CPU 调度的机会越大
		threadPoolExecutor.setThreadPriority(Thread.MAX_PRIORITY);
		// 设置线程是否是守护线程，用户线程会阻止 jvm 关闭，知道所有的用户线程执行完毕，守护线程不会
		threadPoolExecutor.setDaemon(false);
		threadPoolExecutor.setKeepAliveSeconds(keepAliveSeconds);// 线程空闲时间
		threadPoolExecutor.setThreadNamePrefix("core-Executor-"); // 线程名字前缀, 用 core 标识这个是项目核心线程
		// 设置拒绝策略
		threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		return threadPoolExecutor;
	}
}
