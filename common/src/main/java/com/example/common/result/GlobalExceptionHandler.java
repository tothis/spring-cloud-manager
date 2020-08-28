package com.example.common.result;

import com.example.common.exception.ExceptionType;
import com.example.common.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常捕获
 *
 * @author 李磊
 * @since 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public ResultEntity catchGlobalException(GlobalException e) {
        log.error(e.toString());
        ResultEntity entity = new ResultEntity();
        entity.setCode(e.getCode());
        entity.setMessage(e.getMessage());
        return entity;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResultEntity catchRuntimeException(RuntimeException e) {
        log.error(e.toString());
        ResultEntity entity = new ResultEntity();
        entity.setCode(ExceptionType.SYSTEM.getCode());
        entity.setMessage(e.getMessage());
        return entity;
    }

    @ExceptionHandler(Exception.class)
    public ResultEntity catchException(Exception e) {
        log.error(e.toString());
        ResultEntity entity = new ResultEntity();
        entity.setCode(ExceptionType.SYSTEM.getCode());
        entity.setMessage(ExceptionType.SYSTEM.getMessage());
        return entity;
    }
}