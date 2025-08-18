package com.photo.judge.thread;

import org.junit.jupiter.api.Test;

public class ThreadTest {
	private static final String Lock = "lock";


	/**
	 * 模拟 wait 和 notify 的使用
	 */
	@Test
	public void waitTest() throws InterruptedException {
		Thread thread1 = new Thread(() -> {
			synchronized (Lock) {
				try {
					System.out.println("Thread 1 is waiting");
					Lock.wait(); // 等待通知
					System.out.println("Thread 1 is notified and continues");
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					System.out.println("Thread 1 was interrupted");
				}
			}
		});

		Thread thread2 = new Thread(() -> {
			synchronized (Lock) {
				try {
					System.out.println("Thread 2 is sleeping for 2 seconds");
					Thread.sleep(2000); // 模拟一些工作
					System.out.println("Thread 2 is notifying Thread 1");
					Lock.notify(); // 通知等待的线程
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					System.out.println("Thread 2 was interrupted");
				}
			}
		});
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();
	}

	@Test
	public void simulationDeadLock() {
		Object lock1 = new Object();
		Object lock2 = new Object();

		Thread thread1 = new Thread(() -> {
			synchronized (lock1) {
				System.out.println("Thread 1: Holding lock 1...");
				try {
					Thread.sleep(100); // 模拟一些工作
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				System.out.println("Thread 1: Waiting for lock 2...");
				synchronized (lock2) {
					System.out.println("Thread 1: Acquired lock 2!");
				}
			}
		});

		Thread thread2 = new Thread(() -> {
			synchronized (lock2) {
				System.out.println("Thread 2: Holding lock 2...");
				try {
					Thread.sleep(100); // 模拟一些工作
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				System.out.println("Thread 2: Waiting for lock 1...");
				synchronized (lock1) {
					System.out.println("Thread 2: Acquired lock 1!");
				}
			}
		});

		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println("Main thread was interrupted");
		}
	}
}
