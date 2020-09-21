package com.example.user.entity.vo;

import com.example.user.constant.UserDescriptionConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 李磊
 * @since 1.0
 */
@Data
@ApiModel
public class UserLoginRequest {

    @ApiModelProperty(UserDescriptionConstant.USER_NAME)
    private String userName;

    @ApiModelProperty(UserDescriptionConstant.PASSWORD)
    private String password;
}