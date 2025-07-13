package com.photo.judge.common.context;

import lombok.extern.slf4j.Slf4j;

/**
 *  请求的上下文信息
 */
@Slf4j
public class RequestContextHolder {
	/**
	 * 在 Springboot 中，每一个请求是一个线程，我们使用 ThreadLocal 来存储每个请求的上下文信息。
	 * 达到线程之间的隔离性
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
