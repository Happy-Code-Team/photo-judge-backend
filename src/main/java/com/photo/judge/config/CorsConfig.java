package com.photo.judge.config; // 确保包名正确

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
					.allowedOrigins("*") // <--- 暂时允许所有源，但会在下面的 allowedOriginPatterns 中进行更精细控制
					.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
					.allowedHeaders("*")
					.allowCredentials(true)
					// 使用 allowedOriginPatterns 更灵活地匹配源
					.allowedOriginPatterns("http://*:3000", "http://localhost:3000") // <--- 允许所有IP/域名但端口为3000，以及localhost:3000
					// allowedOriginPatterns 不支持通配符 `*` 匹配 IP 的所有部分，
					// 但可以匹配所有域名和IP地址的特定端口
					// 注意：当使用 allowedOriginPatterns 时，allowedOrigins 的 "*" 只是一个占位符
					// 实际的匹配逻辑由 allowedOriginPatterns 处理。
					// 如果你只用 allowedOrigins，那么 allowedOrigins 列表中不能同时有 "*" 和具体的域名
					.maxAge(3600); // 预检请求的缓存时间，单位秒
			}
		};
	}
}