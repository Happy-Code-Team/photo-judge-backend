package com.photo.judge.config;

import ch.qos.logback.core.net.server.Client;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class RedisConfig {
	@Value("${spring.data.redis.host:localhost}")
	private String redisHost;

	@Value("${spring.data.redis.port:6379}")
	private Integer redisPort;

	@Bean(destroyMethod = "shutdown")
	public ClientResources clientResources() {
		return DefaultClientResources.create();
	}

	@Bean
	public RedisConnectionFactory redisConnectionFactory(ClientResources clientResources) {
		// 单机配置
		RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration();
		standaloneConfig.setHostName(redisHost);
		standaloneConfig.setPort(redisPort);

		LettuceClientConfiguration.LettuceClientConfigurationBuilder clientConfigurationBuilder = LettuceClientConfiguration.builder();
		clientConfigurationBuilder
			.commandTimeout(Duration.ofMillis(3000)) // 命令超时时间
			.shutdownTimeout(Duration.ofMillis(100)); // 关闭超时时间

		// 配置客户端选项，例如断开连接行为、重连策略等
		// 这里设置断开连接后不立即失败，而是等待重连
		clientConfigurationBuilder.clientOptions(ClientOptions.builder()
			.disconnectedBehavior(ClientOptions.DisconnectedBehavior.REJECT_COMMANDS) // 断开连接后拒绝命令
			.autoReconnect(true) // 自动重连
			.build());

		clientConfigurationBuilder.clientResources(clientResources);
		return new LettuceConnectionFactory(standaloneConfig, clientConfigurationBuilder.build());
	}

	/**
	 * 注入 RedisTemplate， 供 Spring-Cache 使用
	 * @param redisConnectionFactory redisFactory
	 * @return RedisTemplate
	 */
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		// 设置键的序列化器
		StringRedisSerializer stringSerializer = new StringRedisSerializer();
		template.setKeySerializer(stringSerializer);
		template.setHashKeySerializer(stringSerializer); // 如果使用Hash类型，也需要设置Hash键的序列化
		// 设置值的序列化器为 JSON
		GenericJackson2JsonRedisSerializer jsonSerializer = new GenericJackson2JsonRedisSerializer();
		template.setValueSerializer(jsonSerializer);
		template.setHashValueSerializer(jsonSerializer); // 如果使用Hash类型，也需要设置Hash值的序列化
		template.afterPropertiesSet();
		return template;
	}
}
