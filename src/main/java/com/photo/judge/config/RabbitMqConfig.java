package com.photo.judge.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
	/**
	 * MessageConverter用于将Java对象转换为RabbitMQ的消息。默认情况下，Spring Boot使用SimpleMessageConverter，
	 * 只能发送String和byte[]类型的消息，不太方便。
	 * 使用Jackson2JsonMessageConverter，
	 * 我们就可以发送JavaBean对象，由Spring Boot自动序列化为JSON并以文本消息传递。
	 * @return MessageConverter 实例
	 */
	@Bean
	MessageConverter createMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}
