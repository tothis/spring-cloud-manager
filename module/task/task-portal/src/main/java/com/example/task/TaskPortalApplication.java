package com.example.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 李磊
 * @since 1.0
 */
@ComponentScan("com.example")
@SpringBootApplication
public class TaskPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskPortalApplication.class, args);
    }
}