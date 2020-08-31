package com.example.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Past;
import java.time.LocalDateTime;

import static com.example.common.constant.ValidatedConstant.CREATE_DATE_TIME;
import static com.example.common.constant.ValidatedConstant.UPDATE_DATE_TIME;

/**
 * 基础实体
 *
 * @author 李磊
 * @since 1.0
 */
@Data
public class BaseEntity {

    private Long id;

    @TableField(exist = false)
    @ApiModelProperty(value = "起始页", example = "1")
    private Long pageNum;

    @TableField(exist = false)
    @ApiModelProperty(value = "页显示数量", example = "10")
    private Long pageSize;

    @ApiModelProperty("创建者")
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    @Past(message = CREATE_DATE_TIME)
    private LocalDateTime createDateTime;

    @ApiModelProperty("修改者")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @ApiModelProperty("修改时间")
    @Past(message = UPDATE_DATE_TIME)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateDateTime;

    @ApiModelProperty("状态 0:正常(默认) 1:已删除")
    @TableField(fill = FieldFill.INSERT)
    private Byte state;

    public <T> Page<T> newPage() {
        if (pageNum == null || pageSize == null) {
            pageNum = 0L;
            pageSize = 10L;
        } else {
            pageNum = (pageNum - 1) * pageSize;
        }
        return new Page<>(pageNum, pageSize);
    }
}