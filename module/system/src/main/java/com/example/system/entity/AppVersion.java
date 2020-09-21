package com.example.system.entity;

import com.example.common.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * app版本
 *
 * @author 李磊
 * @since 1.0
 */
@Data
public class AppVersion extends BaseEntity {

    @ApiModelProperty("版本号")
    private String code;

    @ApiModelProperty("系统类型 0:安卓 1:IOS")
    private Byte osType;
}