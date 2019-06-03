package com.wx.redis_pub_sub;

import com.wx.redis_pub_sub.sub.DemoSubscribeHandler;
import com.wx.redis_pub_sub.sub.RedisSubscribeHandler;

public enum RedisPubEnum {
    DEMO_PUB("DEMO_CHANNEL", DemoSubscribeHandler.class);

    private String channel;
    private Class<? extends RedisSubscribeHandler> handlerClass;

    RedisPubEnum(String channel, Class<? extends RedisSubscribeHandler> handlerClass) {
        this.channel = channel;
        this.handlerClass = handlerClass;
    }

    public String getChannel() {
        return channel;
    }

    public Class<? extends RedisSubscribeHandler> getHandlerClass() {
        return handlerClass;
    }
}
