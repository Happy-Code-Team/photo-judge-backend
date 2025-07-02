package com.photo.judge.threadPoolExecutor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SpringBootTest
public class ThreadPoolExecutorTest {
	@Autowired
	private ThreadPoolTaskExecutor threadPoolExecutor;

	/**
	 * Runnable: 适合没有返回值的情况
	 */
	@Test
	public void testRunnable() {
		threadPoolExecutor.execute(() -> {
			System.out.println("Runnable task is running");
			try {
				Thread.sleep(1000); // 模拟任务执行
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); // 恢复中断状态
			}
			System.out.println("Runnable task completed");
		});
	}

	/**
	 * Callable: 适合有返回值的情况
	 * Callable 可以抛出异常，Runnable 不可以
	 */
	@Test
	public void testCallable() {
		Future<String> result = threadPoolExecutor.submit(() -> {
			System.out.println("Callable task is running");
			try {
				Thread.sleep(1000); // 模拟任务执行
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); // 恢复中断状态
			}
			System.out.println("Callable task completed");
			return "hello world"; // Callable 需要返回值，这里返回 null
		});

		try {
			System.out.println("Callable task result: " + result.get());
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}
	}
}
