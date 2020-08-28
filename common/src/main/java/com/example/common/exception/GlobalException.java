package com.example.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 基础异常
 *
 * @author 李磊
 * @since 1.0
 */
@Getter
@Setter
public class GlobalException extends RuntimeException {
    private long code;
    private String message;

    public GlobalException(ExceptionType type) {
        this.code = type.getCode();
        this.message = type.getMessage();
    }
}