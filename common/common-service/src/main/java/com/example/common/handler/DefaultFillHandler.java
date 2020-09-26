package com.example.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.example.common.constant.CommonConstant;
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
    private static final String COMMON_CREATE_DATE = "createDateTime";
    private static final String COMMON_UPDATE_BY = "updateBy";
    private static final String COMMON_UPDATE_DATE = "updateDateTime";
    private static final String COMMON_STATE = "state";

    @Override
    public void insertFill(MetaObject o) {
        log.info("开始填充插入数据");
        // 此处类型与实体属性类型保持一至 实体属性类型为包装类此处也必须为包装类
        this.strictInsertFill(o, COMMON_CREATE_BY, () -> 0L, Long.class);
        this.strictInsertFill(o, COMMON_CREATE_DATE, LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(o, COMMON_UPDATE_BY, () -> 0L, Long.class);
        this.strictInsertFill(o, COMMON_UPDATE_DATE, LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(o, COMMON_STATE, () -> CommonConstant.STATE_NORMAL, Byte.class);
    }

    @Override
    public void updateFill(MetaObject o) {
        log.info("开始填充修改数据");
        this.strictInsertFill(o, COMMON_UPDATE_BY, () -> 1L, Long.class);
        this.strictInsertFill(o, COMMON_UPDATE_DATE, LocalDateTime::now, LocalDateTime.class);
    }
}