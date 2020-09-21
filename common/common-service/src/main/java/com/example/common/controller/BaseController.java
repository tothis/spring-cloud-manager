package com.example.common.controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 基础控制器
 *
 * @author 李磊
 * @since 1.0
 */
public abstract class BaseController {

    protected HttpServletRequest request;

    protected HttpServletResponse response;

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Autowired
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
}