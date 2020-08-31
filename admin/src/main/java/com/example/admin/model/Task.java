package com.example.admin.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.example.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

import static com.example.common.constant.ValidatedConstant.TASK_NAME_BLANK;

/**
 * @author 李磊
 * @since 1.0
 */
@ApiModel("任务")
@Data
public class Task extends BaseEntity {

    @NotBlank(message = TASK_NAME_BLANK)
    @ApiModelProperty("任务名称")
    private String taskName;

    @ApiModelProperty("任务状态 0:关闭(默认) 1:正常")
    @TableField(fill = FieldFill.INSERT)
    private Boolean taskState;
}