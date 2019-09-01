package com.wx.service;

import com.wx.dao.ADao;
import com.wx.entity.po.AEntity;
import com.wx.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTest {

    @Autowired
    private RedisService redisService;
    @Autowired
    private Demo2Service demo2Service;
    @Autowired
    private DemoService demoService;
    @Autowired
    private ADao aDao;

    @Test
    public void Test() {
        redisService.get("aa", String.class);
    }

    @Test
    public void Test1() {
        log.info("你好");
        log.info("你好");
    }

    @Test
    public void mybatisTest() {
        demo2Service.wrapper();
    }

    @Test
    public void mybatisTest1() {
        demoService.step1();
    }

    @Test
    public void SimpleSqlTest(){
        List<AEntity> list =  aDao.selectOne("1");

        list.size();
    }
}
