package com.example.user.entity.vo;

import com.example.user.constant.UserDescriptionConstant;
import com.example.user.constant.UserValidatedConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author 李磊
 * @since 1.0
 */
@Data
public class UserSaveRequest {

    private Long id;

    @ApiModelProperty(UserDescriptionConstant.USER_NAME)
    @Length(message = UserValidatedConstant.USER_NAME_NOT_BLANK)
    @Length(min = UserValidatedConstant.USER_NAME_MIN_VALUE
            , max = UserValidatedConstant.USER_NAME_MAX_VALUE
            , message = UserValidatedConstant.USER_NAME_LENGTH)
    @NotBlank(message = UserValidatedConstant.USER_NAME_NOT_BLANK)
    private String userName;
}