package com.photo.judge.completableFuture;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// CompletableFuture 测试, 任务编排, 说明 ，中间的结果也只能消费一次
public class CompletableFutureTest {
	ExecutorService executor = Executors.newFixedThreadPool(4);

	// 1. 一元依赖，任务2 依赖 任务1 的结果
	@Test
	public void testOneDependency() throws ExecutionException, InterruptedException {
		CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> "hello", executor);
		CompletableFuture<String> task2 = task1.thenApply(result -> {
			return result + " world";
		});
		System.out.println(task2.get());
	}

	// 2. 二元依赖，任务1 和 任务 2 并行，任务 3 依赖 任务1 和 任务2 的结果
	@Test
	public void testTwoDependencies() throws ExecutionException, InterruptedException {
		CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> "hello", executor);
		CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> "world", executor);

		CompletableFuture<String> task3 = task1.thenCombine(task2, (result1, result2) -> {
			return result1 + result2 + "!";
		});

		System.out.println(task3.get());
	}

	// 3. 多元依赖，任务1、任务2、任务3... ，任务 M 依赖任务1、任务2、任务3, ... 的结果
	@Test
	public void testMultipleDependencies() throws ExecutionException, InterruptedException {
		CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> "hello", executor);
		CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> "world", executor);
		CompletableFuture<String> task3 = CompletableFuture.supplyAsync(() -> "from", executor);

		// 组合任务
		CompletableFuture<Void> taskM = CompletableFuture.allOf(task1, task2, task3);

		CompletableFuture<String> result = taskM.thenApply(res -> {
			String result1 = task1.join();
			String result2 = task2.join();
			String result3 = task3.join();
			return result1 + " " + result2 + " " + result3 + "!";
		});


		System.out.println(result.get());
	}

}
