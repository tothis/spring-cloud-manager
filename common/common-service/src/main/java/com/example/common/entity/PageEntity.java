package com.example.common.entity;

import lombok.Data;

import java.util.List;

/**
 * 分页查询对象
 *
 * @author 李磊
 * @since 1.0
 */
@Data
public class PageEntity<T> {
    private List<T> data;
    private long count;

    public PageEntity(long count) {
        this.count = count;
    }
}