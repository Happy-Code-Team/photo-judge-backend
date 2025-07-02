package com.photo.judge.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import static org.junit.jupiter.api.Assertions.*;

/**
 * `@SpringBootTest` 会启动 Spring Boot 应用上下文，让 `@Autowired` 能够注入 RedisTemplate。
 */
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testSetAndGet() {
        String key = "test:key";
        String value = "Hello, Redis!";
        // 写入 Redis
        redisTemplate.opsForValue().set(key, value);
        // 从 Redis 读取
        Object result = redisTemplate.opsForValue().get(key);
        assertEquals(value, result);
    }
}
