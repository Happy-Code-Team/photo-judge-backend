package com.photo.judge.controller.rabbit;

import com.photo.judge.common.rabbitmq.RabbitMqMessageSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 进行 rabbitmq 的测试
 */
@RestController
public class RabbitTestController {

	private final RabbitMqMessageSend rabbitMqMessageSend;

	public RabbitTestController(RabbitMqMessageSend rabbitMqMessageSend) {
		this.rabbitMqMessageSend = rabbitMqMessageSend;
	}

	@GetMapping("/rabbit/test")
	public String testRabbitMq() {
		String exchange = "vou_generate_exchange";
		String routingKey = "vou.generate";
		String message = "Hello, RabbitMQ!";
		// 发送消息到 RabbitMQ
		rabbitMqMessageSend.sendMessage(exchange, routingKey, message);
		return "Message sent to RabbitMQ successfully!";
	}


}
