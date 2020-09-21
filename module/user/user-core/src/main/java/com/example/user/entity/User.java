package com.example.user.entity;

import com.example.common.entity.BaseEntity;
import com.example.user.constant.UserDescriptionConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 李磊
 * @since 1.0
 */
@ApiModel("用户信息")
@Data
public class User extends BaseEntity {

    @ApiModelProperty(UserDescriptionConstant.USER_NAME)
    private String userName;

    @ApiModelProperty(UserDescriptionConstant.PASSWORD)
    private String password;
}