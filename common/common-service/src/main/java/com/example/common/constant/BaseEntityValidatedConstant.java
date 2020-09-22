package com.example.common.constant;

/**
 * 基础实体校验常量
 *
 * @author 李磊
 * @since 1.0
 */
public class BaseEntityValidatedConstant {
    public static final String ID = "id必须为正整数";
    public static final String STATE = "状态必须为正整数";
    public static final String CREATE_BY = "创建者必须为正整数";
    public static final String CREATE_DATE_TIME = "创建时间必须为已过去时间";
    public static final String UPDATE_BY = "修改者必须为正整数";
    public static final String UPDATE_DATE_TIME = "修改时间必须为已过去时间";
    public static final String PAGE_NUM = "开始页数必须为正整数";
    public static final String PAGE_SIZE = "每页数据数量必须为正整数";
}