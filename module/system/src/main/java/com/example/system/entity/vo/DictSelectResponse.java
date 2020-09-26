package com.example.system.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 李磊
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DictSelectResponse {
    private String label;
    private String value;
}