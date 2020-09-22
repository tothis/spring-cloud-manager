package com.example.common.entity.dto;

import lombok.Data;

/**
 * @author 李磊
 * @since 1.0
 */
@Data
public class UserTaskDTO {

    private String taskName;

    private Boolean taskState;

    private String description;
}