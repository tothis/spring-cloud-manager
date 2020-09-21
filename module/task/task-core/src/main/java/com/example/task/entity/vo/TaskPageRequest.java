package com.example.task.entity.vo;

import com.example.task.constant.TaskDescriptionConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 李磊
 * @since 1.0
 */
@Data
public class TaskPageRequest {

    @ApiModelProperty(TaskDescriptionConstant.TASK_NAME)
    private String taskName;

    @ApiModelProperty("起始页")
    private Long pageNum;
}