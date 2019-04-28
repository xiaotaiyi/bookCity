package com.xiaodongbaobao.novel;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

import com.wenwenmao.BaseApiApplication;

@ComponentScan(basePackages = {"com.xiaodongbaobao.novel"})
public class Application extends BaseApiApplication{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
