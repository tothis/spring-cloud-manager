package com.example.task.constant;

/**
 * 任务校验常量
 *
 * @author 李磊
 * @since 1.0
 */
public class TaskValidatedConstant {

    public static final String TASK_NAME_NOT_NULL = "任务名称必填";
    public static final int TASK_NAME_MAX_VALUE = 10;
    public static final String TASK_NAME_MAX = "任务名称名称最多为" + TASK_NAME_MAX_VALUE + "字";

    public static final int DESCRIPTION_MIN_VALUE = 10;
    public static final int DESCRIPTION_MAX_VALUE = 100;
    public static final String DESCRIPTION = "任务描述" + DESCRIPTION_MIN_VALUE + "~"
            + DESCRIPTION_MAX_VALUE + "字";
}