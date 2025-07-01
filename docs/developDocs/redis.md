## Springboot 集成 Redis

[SpringBoot 集成 Redis](https://blog.csdn.net/weixin_43749805/article/details/131399516)

上面的教程说明了使用 `SpringCache` 来集成 Redis 的方式，可以不自己定义 `RedisTemplate`，`SpringRedis` 会提供一个默认的 `RedisTemplate`。

但是，如果我们对序列化，redis 连接池有特殊的需求，需要自己定一个 `RedisTemplate`。当自己定义了 `RedisTemplate` 之后，`SpringCache` 提供的 
`RedisCacheManager` 会自动监测，并使用我们自定义的 `RedisTemplate`。

同时，`Springboot2.x` 版本之后，不用 `Jedis` 了，使用 `Lettuce` 作为默认的 Redis 客户端。 在 `RedisConfig` 中配置 `Lettuce`。 `Redis` 
连接池子在配置文件中配置。
```yaml
spring:
  # redis 配置
  data:
    redis:
      host: localhost
      port: 6379
      connect-timeout: 3000ms
      # lettuce 连接池配置
      lettuce:
        pool:
          enabled: true
          # 最大连接数
          max-active: 1000
          # 连接池最大空闲数
          max-idle: 100
          # 最小连接数
          min-idle: 10
          # 连接池最大等待时间, 当 Redis 连接池没有可用的连接的时候的最大等待时间，超过等待时间则抛出异常, 负数为一直等待，不建议
          max-wait: 3000ms
```
