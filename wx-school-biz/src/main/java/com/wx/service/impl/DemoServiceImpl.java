package com.wx.service.impl;

import com.wx.dao.AUserDao;
import com.wx.dao.BUserDao;
import com.wx.entity.po.AUser;
import com.wx.entity.po.BUser;
import com.wx.redis_pub_sub.RedisPubEnum;
import com.wx.redis_pub_sub.pub.RedisPublisher;
import com.wx.self.BeanSelfAware;
import com.wx.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
public class DemoServiceImpl implements DemoService, BeanSelfAware {

    @Autowired
    private RedisPublisher redisPublisher;
    @Autowired
    private AUserDao aUserDao;
    @Autowired
    private BUserDao bUserDao;

    private DemoService self;


    @Override
    public String addMessage2Redis() {
        redisPublisher.publish(RedisPubEnum.DEMO_PUB.getChannel(), "你好吗，这是测试");
        return "";
    }

    @Override
    public String step1() {
        self.transactionTest();
        return "";
    }


    @Transactional
    @Override
    public String transactionTest() {
        Date date = new Date(System.currentTimeMillis());
        AUser aUser = new AUser("aaa", date);
        BUser bUser = new BUser("bbbb", date);
        int i = bUserDao.insertAUser(bUser);
        if (i != 100) {
            throw new RuntimeException();
        }
        aUserDao.insertAUser(aUser);
        return "";
    }

    @Override
    public void setSelf(Object proxyBean) {
        this.self = (DemoService) proxyBean;
    }
}
