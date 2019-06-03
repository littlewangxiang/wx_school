package com.wx.redis_pub_sub;

import com.wx.redis_pub_sub.sub.RedisSubscribeHandler;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisPubSub;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class RedisPublishListener extends JedisPubSub {

    private static Map<String, RedisSubscribeHandler> handlerMap = new ConcurrentHashMap<>();

    @Override
    public void onMessage(String channel, String message) {
        try {
            log.info("RedisPublishListener receive msg, begin deal by channel:{}", channel);
            RedisSubscribeHandler handler = handlerMap.get(channel);
            if (handler == null) {
                log.info("handlerMap does not contains handler to deal with msg. channel:{}" + channel);
                return;
            }
            handler.handle(message);
            log.info("RedisPublishListener channel:{} deal msg done", channel);
        } catch (Exception e) {
            log.info("RedisPublishListener deal msg error", e);
        }
    }

    public synchronized void registerHandler(String channel, RedisSubscribeHandler handler) {
        log.info("RedisPublishListener registerHandler:" + " channel=" + channel + ", handler=" + handler.getClass());
        handlerMap.put(channel, handler);
    }

    @Override
    public void onPMessage(String s, String s1, String s2) {

    }

    @Override
    public void onSubscribe(String s, int i) {

    }

    @Override
    public void onUnsubscribe(String s, int i) {

    }

    @Override
    public void onPUnsubscribe(String s, int i) {

    }

    @Override
    public void onPSubscribe(String s, int i) {

    }
}
