package com.photo.judge.common.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ 消息监听类
 * 该类用于监听 RabbitMQ 消息队列中的消息。
 */
@Component
public class RabbitMqMessageListen {
	private final String queue = "vou_generate";

	/**
	 * 这种写法是自动 ack 的形式，生产环境中有丢失消息的风险。
	 * 当消息一旦被消费者接受，就认为消息已经被消费了，如果消费者在这个时候崩溃了，
	 * 那么这个消息就会丢失。
	 * @param message
	 */
//	@RabbitListener(queues = queue)
//	public void listenOnVouGenerate(Object message) {
//		System.out.println("接收到的消息: " + message);
//	}

	/**
	 *
	 * @param message 接收到的消息内容，Spring 会自动转换成对应的 Java 对象
	 * @param channel RabbitMQ 的 Channel 对象，用于手动确认或拒绝消息
	 * @param tag 消息的投递标签，用于标识要确认/拒绝的消息
	 */
	@RabbitListener(queues = queue)
	public void listenOnVouGenerate(Object message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
		System.out.println("接收到的消息: " + message);

		try {
			// -------- 你的业务逻辑处理开始 --------
			// 模拟业务处理可能成功或失败
			boolean processSuccess = false; // 假设处理成功
			if (message.toString().contains("fail")) { // 模拟消息内容包含"fail"则处理失败
				processSuccess = false;
			}
			// -------- 你的业务逻辑处理结束 --------

			if (processSuccess) {
				// 消息处理成功：发送 ACK 确认，告诉 RabbitMQ 可以删除这条消息了
				// basicAck(deliveryTag, multiple);
				// multiple 参数为 false 表示只确认当前这条消息
				channel.basicAck(tag, false);
				System.out.println("消息处理成功并已 ACK: " + message);
			} else {
				// 消息处理失败：发送 NACK 拒绝，并决定是否重新入队
				// basicNack(deliveryTag, multiple, requeue);
				// multiple 参数为 false 表示只拒绝当前这条消息
				// requeue 参数为 false 表示不重新入队（消息将根据队列的死信配置进入死信队列）
				// requeue 参数为 true 表示重新入队（消息会回到队列头部，可能被其他消费者或自己再次消费）
				channel.basicNack(tag, false, false); // 不重新入队，通常配合死信队列使用
				System.out.println("消息处理失败并已 NACK (不重新入队): " + message);
			}
		} catch (Exception e) {
			// 捕获处理消息时可能发生的任何异常
			System.err.println("处理消息时发生异常: " + message + ", 错误: " + e.getMessage());
			try {
				// 当发生异常时，通常也选择 NACK 并且不重新入队，让消息进入死信队列
				channel.basicNack(tag, false, false);
			} catch (Exception nackException) {
				System.err.println("NACK 消息时发生异常: " + nackException.getMessage());
			}
		}
	}

	/**
	 * 监听死信队列中的消息, 监听失败的消息，处理失败的消息
	 * @param message 接收到的死信消息内容
	 * @param channel RabbitMQ 的 Channel 对象
	 * @param tag 消息的投递标签
	 */
	@RabbitListener(queues = "dlq.queue")
	public void processDeadLetterMessage(Object message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {

		try {
			System.out.println("接收到的死信消息: " + message);
			// -------- 这里是处理死信消息的业务逻辑 --------
			// 1. **记录日志：** 详细记录消息内容、原始路由键、进入死信队列的时间等信息。
			// 2. **报警：** 如果是关键业务消息，触发短信、邮件、钉钉等报警通知。
			// 3. **分析：** 可以将消息保存到数据库，供后续人工分析或重试。
			// 4. **（可选）重试：** 如果某些类型的死信可以通过重试解决，可以将其重新发送到原始队列或另一个重试队列。
			//    但要非常小心重试的逻辑，避免无限循环。

			// 假设死信消息处理成功（例如，只是记录下来并报警）
			// 手动 ACK 确认，将死信从死信队列中移除
			channel.basicAck(tag, false);

		} catch (Exception e) {
			// 处理死信消息时如果也发生异常
			try {
				// 死信处理失败通常不再重试，直接 NACK 丢弃或手动干预
				// basicNack(deliveryTag, multiple, requeue)
				// 在这里通常设置为 false, false 表示不重新入队也不进入二级死信（如果配置了的话）
				channel.basicNack(tag, false, false);
			} catch (Exception nackException) {
			}
		}
	}
}
