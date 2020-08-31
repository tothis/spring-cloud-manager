package com.example.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
    private static final String COMMON_CREATE_DATE_TIME = "createDateTime";
    private static final String COMMON_UPDATE_BY = "updateBy";
    private static final String COMMON_UPDATE_DATE_TIME = "updateDateTime";
    private static final String COMMON_STATE = "state";

    private static final String TASK_STATE = "taskState";

    @Override
    public void insertFill(MetaObject o) {
        log.info("开始填充插入数据");
        this.strictInsertFill(o, COMMON_CREATE_BY, () -> 0L, Long.class);
        this.strictInsertFill(o, COMMON_CREATE_DATE_TIME, () -> LocalDateTime.now(), LocalDateTime.class);
        this.strictInsertFill(o, COMMON_UPDATE_BY, () -> 0L, Long.class);
        this.strictInsertFill(o, COMMON_UPDATE_DATE_TIME, () -> LocalDateTime.now(), LocalDateTime.class);
        this.strictInsertFill(o, COMMON_STATE, () -> (byte) 0, Byte.class);

        this.strictInsertFill(o, TASK_STATE, () -> false, Boolean.class);
    }

    @Override
    public void updateFill(MetaObject o) {
        log.info("开始填充修改数据");
        this.strictInsertFill(o, COMMON_UPDATE_BY, () -> 1L, Long.class);
        this.strictInsertFill(o, COMMON_UPDATE_DATE_TIME, () -> LocalDateTime.now(), LocalDateTime.class);
    }
}