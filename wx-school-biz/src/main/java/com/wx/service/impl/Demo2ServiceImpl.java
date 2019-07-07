package com.wx.service.impl;

import com.wx.service.Demo2Service;
import com.wx.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Demo2ServiceImpl implements Demo2Service {

    @Autowired
    private DemoService demoService;


    @Override
    public void wrapper() {
        demoService.step1();
    }
}
