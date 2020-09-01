package com.example.common.type;

import lombok.Getter;

/**
 * 消息类型
 * <p>
 * 100_100_100 项目_模块_功能
 *
 * @author 李磊
 * @since 1.0
 */
@Getter
public enum MessageType {
    OK(0, "请求成功"),
    SYSTEM_ERROR(-1, "系统错误"),
    PARAMETER_ERROR(-2, "参数错误"),

    /* 登录相关 */
    TOKEN_BLANK(100_100_101, "token不可为空");
    private long code;
    private String message;

    MessageType(long code, String message) {
        this.code = code;
        this.message = message;
    }
}