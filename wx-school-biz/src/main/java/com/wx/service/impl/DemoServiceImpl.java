package com.wx.service.impl;

import com.wx.redis_pub_sub.RedisPubEnum;
import com.wx.redis_pub_sub.pub.RedisPublisher;
import com.wx.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private RedisPublisher redisPublisher;


    @Override
    public String addMessage2Redis() {
        redisPublisher.publish(RedisPubEnum.DEMO_PUB.getChannel(), "你好吗，这是测试");
        return "";
    }
}
