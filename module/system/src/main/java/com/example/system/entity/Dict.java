package com.example.system.entity;

import com.example.common.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 字典
 *
 * @author 李磊
 * @since 1.0
 */
@Data
public class Dict extends BaseEntity {

    @ApiModelProperty("字典类型id")
    private Long dictTypeId;

    @ApiModelProperty("字典名称")
    private String label;

    @ApiModelProperty("字典值")
    private String value;
}