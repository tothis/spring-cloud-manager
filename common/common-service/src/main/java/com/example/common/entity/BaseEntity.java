package com.example.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.constant.CommonConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础实体
 *
 * @author 李磊
 * @since 1.0
 */
@Data
public class BaseEntity implements Serializable {

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
    private LocalDateTime createDateTime;

    @ApiModelProperty("修改者")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateDateTime;

    @ApiModelProperty("状态 0:正常 1:已删除")
    @TableField(fill = FieldFill.INSERT)
    private Byte state;

    public static <T> Page<T> newPage(Long pageNum, Long pageSize) {
        return new Page<>((pageNum - 1) * pageSize, pageSize);
    }

    public static <T> Page<T> newPage(Long pageNum) {
        return newPage(pageNum, CommonConstant.PAGE_SIZE);
    }
}