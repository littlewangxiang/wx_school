package com.wx.redis;

import redis.clients.jedis.Jedis;

public interface RedisService {

    <T> T get(String key, Class<T> type);

    Boolean set(String key, Object val);

    Jedis getJedis();
}
