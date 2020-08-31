package com.example.common.entity;

import com.example.common.type.MessageType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回数据
 *
 * @author 李磊
 * @since 1.0
 */
@NoArgsConstructor
@Data
public class ResultEntity<T> {
    private long code;
    private String message;
    private T data;

    public ResultEntity(MessageType type) {
        this.code = type.getCode();
        this.message = type.getMessage();
    }
}