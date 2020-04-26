package com.cn.wmd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Descripation:
 * @Author: wmd
 * @Date: 2020-04-21 18:55
 * @since 1.0
 */
@EnableAsync
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);


    }


}