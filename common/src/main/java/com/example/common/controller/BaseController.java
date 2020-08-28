package com.example.common.controller;

import com.example.common.result.ResultEntity;

/**
 * 基础控制器
 *
 * @author 李磊
 * @since 1.0
 */
public abstract class BaseController {

    public static final ResultEntity SUCCESS = ok(null);

    public static final <T> ResultEntity<T> ok(T data) {
        ResultEntity<T> entity = new ResultEntity<>();
        entity.setCode(0);
        entity.setMessage("请求成功");
        entity.setData(data);
        return entity;
    }
}