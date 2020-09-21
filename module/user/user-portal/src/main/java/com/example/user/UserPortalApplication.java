package com.example.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 李磊
 * @since 1.0
 */
@EnableFeignClients
@ComponentScan("com.example")
@SpringBootApplication
public class UserPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserPortalApplication.class, args);
    }
}