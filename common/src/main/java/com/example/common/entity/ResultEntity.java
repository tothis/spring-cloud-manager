package com.example.common.entity;

import lombok.Data;

/**
 * 统一返回数据
 *
 * @author 李磊
 * @since 1.0
 */
@Data
public class ResultEntity<T> {
    private long code;
    private String message;
    private T data;
}