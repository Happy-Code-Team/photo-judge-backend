package com.photo.judge.common.context;

import lombok.extern.slf4j.Slf4j;

/**
 *  请求的上下文信息
 */
@Slf4j
public class RequestContextHolder {
	/**
	 * 在 Springboot 中，每一个请求是一个线程，我们使用 ThreadLocal 来存储每个请求的上下文信息。
	 * 达到线程之间的隔离性。
	 *
	 * ThreadLocal 常见的问题：内存泄漏。什么情况下会发生？
	 * 从 ThreadLocal 的实现机制来说，ThreadLocal 本身是存在每个线程自己的 Map(ThreadLocalMap) 中的。就是这个 Map 的 key 就是 threadLocal
	 * 对象本身，value 就是 threadLocal 存储的值。然后 threadLocal 对象本身是构造的一个弱引用对象，如果在这样的场景中：
	 * <pre>
	 * <code>
	 * public void someMethod() {
	 *     ThreadLocal<String> threadLocal = new ThreadLocal<>();
	 *     ...
	 *     threadLocal.set("some value");
	 *     // 这里没有调用 threadLocal.remove() 或者 threadLocal.set(null)
	 * }
	 * </code>
	 * </pre>
	 * 那当这个方法结束的时候，threadLocal 对象会被垃圾回收器回收，但是它的值 "some value" 仍然会保存在 ThreadLocalMap 中。
	 * 因为 value 被 threadLocalMap 强引用着，所以它不会被回收。 但是如果这个线程销毁了，ThreadLocalMap 也会被销毁，也不会造成
	 * 内存泄漏。但是在实际的开发过程中，我们不会频繁的创建线程和销毁线程。包括 tomcat 和 线程池，都是这样的,都是复用了线程，所以 ThreadLocalMap 也不会被销毁。
	 * 如果你不清除 value 的话，那么这个值就会一直存在于 ThreadLocalMap 中，直到线程被销毁。就会造成内存泄漏。
	 */
	private static final ThreadLocal<com.photo.judge.model.entity.context.RequestContext> contextHolder = new ThreadLocal<>();

	public static void setContext(com.photo.judge.model.entity.context.RequestContext context) {
		contextHolder.set(context);
	}

	public static com.photo.judge.model.entity.context.RequestContext getContext() {
		return contextHolder.get();
	}

	/**
	 * 在离开请求的时候，一定要清除 ThreadLocal 中的内容，避免内存泄漏。同时，tomcat 的线程会复用，
	 * 如果不清除也会造成问题。因为我们的 ThreadLocal 设定的是一个 static final 的，实际不清除，
	 * 每次进入 set，也会覆盖，也不会造成内存泄漏。但是，在没覆盖，或者覆盖之前，覆盖之后，旧值可能
	 * 还要存活一段时间，会无端的占用内存。
	 */
	public static void clear() {
		log.info("清除请求上下文: {}", contextHolder.get().getRequestId());
		contextHolder.remove();
	}
}
