package com.wx.redis_pub_sub;

import com.wx.redis.RedisService;
import com.wx.redis_pub_sub.sub.RedisSubscribeHandler;
import com.wx.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

import java.beans.Introspector;
import java.util.ArrayList;
import java.util.List;

/**
 * 订阅者管理
 */
@Configuration
@Slf4j
public class RedisSubscribeManager {

    @Autowired
    private RedisService redisService;

    @ConditionalOnBean
    @Bean
    public SpringContextUtil init() {
        log.info("[subscribe] enter RedisSubscribeManager init");
        final RedisPublishListener redisPubListener = new RedisPublishListener();

        for (RedisPubEnum pub : RedisPubEnum.values()) {
            try {
                redisPubListener.registerHandler(pub.getChannel(), (RedisSubscribeHandler) SpringContextUtil.getBean
                        (Introspector.decapitalize(pub.getHandlerClass().getSimpleName())));
            } catch (Exception e) {
                log.warn("RedisSubscribeManager init error.", e);
            }
        }

        //this thread will wait here infinitely
        new Thread(() -> {
            try {
                Jedis jedis = redisService.getJedis();
                List<String> channels = new ArrayList<>();
                for (RedisPubEnum pub : RedisPubEnum.values()) {
                    channels.add(pub.getChannel());
                }
                log.info("begin to initialize pubSub");
                jedis.subscribe(redisPubListener, channels.toArray(new String[channels.size()]));
                log.info("end to pubSub initialized.");
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }, "redisRefresh_thread").start();
        return new SpringContextUtil();
    }

}
