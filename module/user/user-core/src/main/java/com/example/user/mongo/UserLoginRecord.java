package com.example.user.mongo;

import com.example.common.entity.BaseEntity;
import com.example.user.constant.UserDescriptionConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 用户登录记录
 *
 * @author 李磊
 * @since 1.0
 */
@Data
@Document
public class UserLoginRecord extends BaseEntity {

    @Id
    private Long id;

    @ApiModelProperty(UserDescriptionConstant.USER_NAME)
    private String userName;
}