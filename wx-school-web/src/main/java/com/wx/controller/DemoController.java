package com.wx.controller;

import com.alibaba.fastjson.JSONObject;
import com.wx.entity.bo.JSONTestParam;
import com.wx.service.AService;
import com.wx.service.DemoService;
import com.wx.service.websocket.ChatWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/admin")
public class DemoController {

    @Autowired
    private DemoService demoService;
    @Autowired
    private AService aService;
    @Autowired
    private ChatWebSocket chatWebSocket;

    @RequestMapping("/test")
    public Object demo() {
        return demoService.addMessage2Redis();
    }

    @PostMapping("/haha")
    public Object test(@RequestParam String name, @RequestParam Integer age, @RequestParam String className) {
//        AEntity aEntity = aService.selectOne();
//        String res = "";
//        if (aEntity != null) {
//            res = StringUtils.isBlank(aEntity.getInfo()) ? "" : aEntity.getInfo();
//        }

        return "年终总结:" + className + "的" + name + "今年" + age + "岁了";
    }

    @GetMapping("/ha")
    public Object test1(@RequestParam("name") String name, @RequestParam("age") String age, @RequestParam("name") String
            className) {
        return "年终总结:" + className + "的" + name + "今年" + age + "岁了";
    }

    @PostMapping("/jsonTest")
    public Object jsonTest(@RequestBody JSONTestParam jsonTestParam) {
        return "年终总结:" + jsonTestParam.getClassName() + "的" + jsonTestParam.getName() + "今年" +
                jsonTestParam.getAge() + "岁了";
    }

    @RequestMapping("/http")
    public Object servletTest(HttpServletRequest request) {
        request.getMethod();
        StringBuilder result = new StringBuilder();
        try {
            InputStream inputStream = request.getInputStream();

            byte[] bytes = new byte[1024];
            while (inputStream.read(bytes) != -1) {
                result.append(new String(bytes, "utf-8"));
            }
            JSONObject jsonObject = JSONObject.parseObject(result.toString());
            jsonObject.containsKey("name");
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.toString();
        return "";
    }

    @RequestMapping("/socket")
    public Object socket(@RequestParam("odds") String odds) {
        chatWebSocket.noticeClient(odds);
        return "success";
    }

}
