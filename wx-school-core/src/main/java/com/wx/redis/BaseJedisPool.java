package com.wx.redis;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class BaseJedisPool {

    @Bean
    public JedisPool jedisPool(@Qualifier("jedis.config") JedisPoolConfig config,
                               @Value("${spring.redis.host}") String host, @Value("${spring.redis.port}") String port) {

        return new JedisPool(config, host, Integer.valueOf(port));
    }
}
