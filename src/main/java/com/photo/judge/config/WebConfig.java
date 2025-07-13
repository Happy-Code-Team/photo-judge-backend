package com.photo.judge.config;

import com.photo.judge.common.context.RequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	private final RequestInterceptor requestInterceptor;

	public WebConfig(RequestInterceptor requestInterceptor) {
		this.requestInterceptor = requestInterceptor;
	}

	/**
	 * 注册拦截器，来提供上下文的插入和清除的功能
	 * @param registry
	 */
	@Override
	public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
		registry.addInterceptor(requestInterceptor)
				.addPathPatterns("/**") // 拦截所有请求
				.excludePathPatterns("/static/**", "/error"); // 排除静态资源和错误处理路径
	}
}
