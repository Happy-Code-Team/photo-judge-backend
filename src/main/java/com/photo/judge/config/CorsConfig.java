package com.photo.judge.config; // 确保包名正确

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置
 */
@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        // 仅使用 allowedOriginPatterns 来指定允许的来源
                        // 这里的模式会负责匹配请求的 Origin
                        .allowedOriginPatterns("http://*:3000", "http://localhost:3000")
                        .maxAge(3600); // 预检请求的缓存时间，单位秒
            }
        };
    }
}