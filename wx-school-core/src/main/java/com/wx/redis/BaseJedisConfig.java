package com.wx.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;

@Slf4j
@Configuration
public class BaseJedisConfig {

    @Bean("jedis.config")
    public JedisPoolConfig jedisPoolConfig(
            @Value("${spring.redis.pool.min-idle}") int minIdle,
            @Value("${spring.redis.pool.max-idle}") int maxIdle,
            @Value("${spring.redis.pool.max-wait}") int maxWaitMillis,
            @Value("${spring.redis.pool.block-when-exhausted}") boolean blockWhenExhausted,
            @Value("${spring.redis.pool.max-total}") int maxTotal) {

        log.info("jedisPoolConfig init");
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMinIdle(minIdle);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setMaxTotal(maxTotal);
        // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
        config.setBlockWhenExhausted(blockWhenExhausted);
        // 是否启用pool的jmx管理功能, 默认true
        config.setJmxEnabled(true);
        return config;

    }
}
