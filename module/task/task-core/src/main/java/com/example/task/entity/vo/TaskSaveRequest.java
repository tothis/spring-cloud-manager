package com.example.task.entity.vo;

import com.example.task.constant.TaskDescriptionConstant;
import com.example.task.constant.TaskValidatedConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author 李磊
 * @since 1.0
 */
@Data
public class TaskSaveRequest {

    private Long id;

    @ApiModelProperty(value = TaskDescriptionConstant.TASK_NAME
            , example = "任务名称")
    @Length(max = TaskValidatedConstant.TASK_NAME_MAX_VALUE
            , message = TaskValidatedConstant.TASK_NAME_MAX)
    @NotBlank(message = TaskValidatedConstant.TASK_NAME_NOT_BLANK)
    private String taskName;

    @ApiModelProperty(value = TaskDescriptionConstant.DESCRIPTION
            , example = "任务描述任务描述任务描述")
    @Length(min = TaskValidatedConstant.DESCRIPTION_MIN_VALUE
            , max = TaskValidatedConstant.DESCRIPTION_MAX_VALUE
            , message = TaskValidatedConstant.DESCRIPTION)
    private String description;
}