package com.example.system.entity.vo;

import lombok.Data;

/**
 * @author 李磊
 * @since 1.0
 */
@Data
public class DictSaveRequest {

    private Long id;

    private Long dictTypeId;

    private String label;

    private String value;
}