package com.photo.judge.common.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ 消息发送类
 * 该类用于发送消息到指定的 RabbitMQ 交换机和路由键。
 * 使用 Spring 的 RabbitTemplate 来简化消息发送操作。
 */
@Component
public class RabbitMqMessageSend {
	@Autowired
	private RabbitTemplate rabbitTemplate;

	/**
	 * @param exchange 对应交换机
	 * @param routingKey 对应的路由键
	 * @param message 具体的消息内容
	 */
	public void sendMessage(String exchange, String routingKey, Object message) {
		// 发送消息到指定的交换机和路由键
		rabbitTemplate.convertAndSend(exchange, routingKey, message);
	}
}
