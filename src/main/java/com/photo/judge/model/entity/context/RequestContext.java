package com.photo.judge.model.entity.context;

import lombok.Data;

/**
 * 请求的上下文信息
 */
@Data
public class RequestContext {
	// 前端每一次请求的 ID，如果有需要，可以在程序中获取, 可以用来做链路追踪
	private String requestId;
}
