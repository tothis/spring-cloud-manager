package com.example.common.controller;

import com.example.common.entity.ResultEntity;
import com.example.common.exception.GlobalException;
import com.example.common.type.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import static com.example.common.constant.ValidatedConstant.ID_FORMAT;
import static com.example.common.type.MessageType.PARAMETER_ERROR;

/**
 * 基础控制器
 *
 * @author 李磊
 * @since 1.0
 */
public abstract class BaseController<M> {

    public static final ResultEntity OK = ok(null);

    @Autowired
    protected M baseService;

    public static final <T> ResultEntity<T> ok(T data) {
        ResultEntity<T> result = new ResultEntity<>();
        result.setCode(MessageType.OK.getCode());
        result.setMessage(MessageType.OK.getMessage());
        result.setData(data);
        return result;
    }

    @ModelAttribute
    private void checkId(@PathVariable(required = false) Long id) {
        // id小于1
        if (id != null && id.compareTo(1L) == -1) {
            throw new GlobalException(PARAMETER_ERROR.getCode(), ID_FORMAT);
        }
    }
}