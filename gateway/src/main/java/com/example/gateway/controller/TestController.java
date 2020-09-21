package com.example.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李磊
 * @since 1.0
 */
@RefreshScope
@RestController
public class TestController {

    @Value("${test:1234}")
    private String test;

    @GetMapping
    public String test() {
        return test;
    }
}