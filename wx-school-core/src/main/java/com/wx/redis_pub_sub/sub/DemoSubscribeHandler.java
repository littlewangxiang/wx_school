package com.wx.redis_pub_sub.sub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DemoSubscribeHandler extends RedisSubscribeHandler {

    @Override
    public void handle(String message) {
        log.info("DemoSubscribeHandler 订阅者开始处理消息。origin msg:{}", message);
        //do business
        log.info("DemoSubscribeHandler 订阅者处理完毕。");
    }
}
