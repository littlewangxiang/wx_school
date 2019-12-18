package com.wx.service;


import com.wx.entity.po.AUser;

import java.util.Map;
import java.util.Optional;

public interface UserLoginService {

    Map<String, Object> login(String userName, String password);

    Optional<AUser> getTestAUser(Integer userId);
}
