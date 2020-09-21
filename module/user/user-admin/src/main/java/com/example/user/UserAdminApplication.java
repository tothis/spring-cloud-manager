package com.example.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.example")
@SpringBootApplication
public class UserAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserAdminApplication.class, args);
    }
}