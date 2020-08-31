package com.example.common.controller;

import com.example.common.entity.ResultEntity;
import com.example.common.exception.GlobalException;
import com.example.common.type.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import static com.example.common.constant.ValidatedConstant.ID_FORMAT;

/**
 * 基础控制器
 *
 * @author 李磊
 * @since 1.0
 */
public abstract class BaseController<M> {

    public static final ResultEntity OK = new ResultEntity<>(MessageType.OK);

    @Autowired
    protected M baseService;

    public static final <T> ResultEntity<T> ok(T data) {
        return new ResultEntity<T>(MessageType.OK) {{
            setData(data);
        }};
    }

    @ModelAttribute
    private void checkId(@PathVariable(required = false) Long id) {
        // id小于1
        if (id != null && id.compareTo(1L) == -1) {
            throw new GlobalException(ID_FORMAT);
        }
    }
}