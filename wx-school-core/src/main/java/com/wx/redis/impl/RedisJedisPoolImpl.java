package com.wx.redis.impl;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.wx.constant.CommonConstant;
import com.wx.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;

@Service
public class RedisJedisPoolImpl implements RedisService {

    private static Log log = LogFactory.getLog(RedisJedisPoolImpl.class);

    @Autowired
    private JedisPool jedisPool;

    @Override
    public <T> T get(String key, Class<T> type) {
        T command = new RedisCommand<T>() {
            @Override
            T execute(Jedis jedis) throws IOException {
                return (T) jedis.get(key);
            }

            @Override
            Boolean checkCommandParam() {
                return Boolean.FALSE;
            }
        }.command();
        return command;
    }

    @Override
    public Boolean set(String key, Object val) {
        Boolean commandResult = new RedisCommand<Boolean>() {
            @Override
            Boolean execute(Jedis jedis) throws IOException {
                return CommonConstant.REDIS_DEFAULT_OK.equals(jedis.set(key, val.toString()).toLowerCase());
            }

            @Override
            Boolean checkCommandParam() {
                return Boolean.FALSE;
            }
        }.command();
        return commandResult;
    }

    abstract class RedisCommand<T> {
        abstract T execute(Jedis jedis) throws IOException;

        abstract Boolean checkCommandParam();

        public T command() {
            if (checkCommandParam()) {
                return null;
            }
            Jedis jedis = getJedis();

            try {
                return execute(jedis);
            } catch (Exception e) {
                log.error("redis command excute error", e);
            } finally {
                jedis.close();
            }
            return null;
        }
    }

    @Override
    public Jedis getJedis() {
        return jedisPool.getResource();
    }
}
