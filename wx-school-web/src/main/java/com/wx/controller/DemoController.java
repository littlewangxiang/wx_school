package com.wx.controller;

import com.wx.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/test")
    public Object demo() {
        return demoService.addMessage2Redis();
    }

    @RequestMapping("/haha")
    public Object test() {
        return "haha";
    }

}
