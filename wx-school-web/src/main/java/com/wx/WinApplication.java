package com.wx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.wx"})
public class WinApplication {
    public static void main(String[] args) {
        SpringApplication.run(WinApplication.class, args);
    }
}
