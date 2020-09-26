package com.example.system.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author 李磊
 * @since 1.0
 */
@Data
public class DictListResponse {

    private Long id;

    private String name;

    private List<Item> children;

    @Data
    static class Item {

        private Long id;

        private String label;

        private String value;
    }
}