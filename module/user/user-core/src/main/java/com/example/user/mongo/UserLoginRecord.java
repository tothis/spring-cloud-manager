package com.example.user.mongo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * 用户登录记录
 *
 * @author 李磊
 * @since 1.0
 */
@Data
@Document
public class UserLoginRecord {

    private String userName;

    private LocalDateTime createDateTime;
}