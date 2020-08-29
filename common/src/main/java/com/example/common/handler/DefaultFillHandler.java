package com.example.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 新增或修改时填充数据
 *
 * @author 李磊
 * @since 1.0
 */
@Slf4j
@Component
public class DefaultFillHandler implements MetaObjectHandler {

    private static final String COMMON_CREATE_BY = "createBy";
    private static final String COMMON_CREATE_DATE = "createDate";
    private static final String COMMON_UPDATE_BY = "updateBy";
    private static final String COMMON_UPDATE_DATE = "updateDate";
    private static final String COMMON_STATE = "state";

    private static final String TASK_STATE = "taskState";

    @Override
    public void insertFill(MetaObject o) {
        log.info("开始填充插入数据");
        this.strictInsertFill(o, COMMON_CREATE_BY, () -> 0L, Long.class);
        this.strictInsertFill(o, COMMON_CREATE_DATE, () -> new Date(), Date.class);
        this.strictInsertFill(o, COMMON_UPDATE_BY, () -> 0L, Long.class);
        this.strictInsertFill(o, COMMON_UPDATE_DATE, () -> new Date(), Date.class);
        this.strictInsertFill(o, COMMON_STATE, () -> (byte) 0, Byte.class);

        this.strictInsertFill(o, TASK_STATE, () -> false, Boolean.class);
    }

    @Override
    public void updateFill(MetaObject o) {
        log.info("开始填充修改数据");
        this.strictInsertFill(o, COMMON_UPDATE_BY, () -> 1L, Long.class);
        this.strictInsertFill(o, COMMON_UPDATE_DATE, () -> new Date(), Date.class);
    }
}