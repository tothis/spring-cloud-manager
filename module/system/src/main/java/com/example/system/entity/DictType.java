package com.example.system.entity;

import com.example.common.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 字典类型
 *
 * @author 李磊
 * @since 1.0
 */
@Data
public class DictType extends BaseEntity {

    @ApiModelProperty("字典名称")
    private String name;
}