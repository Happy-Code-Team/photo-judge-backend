package com.photo.judge.common.context;

import com.photo.judge.common.utils.SnowflakeUtils;
import com.photo.judge.model.entity.context.RequestContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestInterceptor implements HandlerInterceptor {
	/**
	 * 拦截器方法，在请求处理之前执行
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		RequestContext requestContext = new RequestContext();
		requestContext.setRequestId(SnowflakeUtils.generateId());
		RequestContextHolder.setContext(requestContext);
		return true; // 返回 true 继续请求处理
	}

	/**
	 * 在整个请求完成之后调用，包括视图渲染完成或抛出异常。
	 * 清楚 traceId
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		RequestContextHolder.clear(); // 清理 Trace ID
	}
}
