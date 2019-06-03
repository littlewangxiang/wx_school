package com.wx.redis_pub_sub.pub;


import com.wx.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class RedisPublisher {

    @Autowired
    private RedisService redisService;

    public Long publish(String channel, String message) {
        if (StringUtils.isBlank(channel) || StringUtils.isBlank(message)) {
            return 0L;
        }
        log.info("Redis public channel:{} message:{}", channel, message);
        return redisService.getJedis().publish(channel, message);
    }

}
