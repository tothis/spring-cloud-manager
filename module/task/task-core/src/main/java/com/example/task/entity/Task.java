package com.example.task.entity;

import com.example.common.entity.BaseEntity;
import com.example.task.constant.TaskDescriptionConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 李磊
 * @since 1.0
 */
@ApiModel("任务")
@Data
public class Task extends BaseEntity {

    @ApiModelProperty(TaskDescriptionConstant.TASK_NAME)
    private String taskName;

    @ApiModelProperty(TaskDescriptionConstant.TASK_STATE)
    private Boolean taskState;

    @ApiModelProperty(TaskDescriptionConstant.DESCRIPTION)
    private String description;
}