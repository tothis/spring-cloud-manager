package com.example.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.example.common.constant.BaseEntityDescriptionConstant;
import com.example.common.constant.BaseEntityValidatedConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
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

    @ApiModelProperty(BaseEntityDescriptionConstant.ID)
    @Min(value = 0, message = BaseEntityValidatedConstant.ID)
    private Long id;

    @ApiModelProperty(BaseEntityDescriptionConstant.PAGE_NUM)
    @Min(value = 0, message = BaseEntityValidatedConstant.PAGE_NUM)
    @TableField(exist = false)
    private Long pageNum;

    @ApiModelProperty(BaseEntityDescriptionConstant.PAGE_SIZE)
    @Min(value = 0, message = BaseEntityValidatedConstant.PAGE_SIZE)
    @TableField(exist = false)
    private Long pageSize;

    @ApiModelProperty(BaseEntityDescriptionConstant.CREATE_BY)
    @Min(value = 0, message = BaseEntityValidatedConstant.CREATE_BY)
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(BaseEntityDescriptionConstant.CREATE_DATE_TIME)
    @Past(message = BaseEntityValidatedConstant.CREATE_DATE_TIME)
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDateTime;

    @ApiModelProperty(BaseEntityDescriptionConstant.UPDATE_BY)
    @Min(value = 0, message = BaseEntityValidatedConstant.UPDATE_BY)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @ApiModelProperty(BaseEntityDescriptionConstant.UPDATE_DATE_TIME)
    @Past(message = BaseEntityValidatedConstant.UPDATE_DATE_TIME)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateDateTime;

    @ApiModelProperty(BaseEntityDescriptionConstant.STATE)
    @Min(value = 0, message = BaseEntityValidatedConstant.STATE)
    @TableField(fill = FieldFill.INSERT)
    private Byte state;
}