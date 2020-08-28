package com.example.common.exception;

import lombok.Getter;

/**
 * 异常类型
 * <p>
 * 100_100_100 项目_模块_功能
 *
 * @author 李磊
 * @since 1.0
 */
@Getter
public enum ExceptionType {
    // OK(0, "请求成功"),
    SYSTEM(-1, "系统错误"),
    BLANK(100_100_101, "用户名不能为空");
    private long code;
    private String message;

    ExceptionType(long code, String message) {
        this.code = code;
        this.message = message;
    }
}