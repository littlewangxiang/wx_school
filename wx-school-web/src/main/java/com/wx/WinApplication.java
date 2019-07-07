package com.wx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.wx"})
@MapperScan("com.wx.dao")
public class WinApplication {
    public static void main(String[] args) {
        SpringApplication.run(WinApplication.class, args);
    }
}
