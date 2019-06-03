package com.wx.redis_pub_sub.sub;

public abstract class RedisSubscribeHandler {
    public abstract void handle(String message);
}
