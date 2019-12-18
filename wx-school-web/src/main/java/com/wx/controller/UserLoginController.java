package com.wx.controller;

import com.wx.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;

    @RequestMapping("/login")
    public Object userLogin(@RequestParam("userName") String userName, @RequestParam("password") String password) {

        return userLoginService.login(userName, password);
    }
}
