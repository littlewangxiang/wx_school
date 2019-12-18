package com.wx.service.impl;

import com.wx.entity.po.AUser;
import com.wx.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Override
    public Map<String, Object> login(String userName, String password) {

        return getUserInfo(userName, password);
    }

    @Override
    public Optional<AUser> getTestAUser(Integer userId) {
        if (userId == null) {
            return Optional.empty();
        }
        return Optional.of(new AUser("111", new Date(System.currentTimeMillis())));
    }

    private Map<String, Object> getUserInfo(String userName, String password) {
        Map<String, Object> result = new HashMap<>();
        String userId = "12345";
        String token = "abcdeef";
        if ("王祥".equals(userName)) {
            userId = "9876543";
        }
        token = token + userId;
        result.put("userId", userId);
        result.put("token", token);
        return result;
    }
}
