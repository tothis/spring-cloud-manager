package com.example.common.exception;

import com.example.common.type.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 基础异常
 *
 * @author 李磊
 * @since 1.0
 */
@AllArgsConstructor
@Getter
public class GlobalException extends RuntimeException {
    private long code;
    private String message;

    public GlobalException(MessageType type) {
        this.code = type.getCode();
        this.message = type.getMessage();
    }
}