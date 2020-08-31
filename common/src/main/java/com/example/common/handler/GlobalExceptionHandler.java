package com.example.common.handler;

import com.example.common.entity.ResultEntity;
import com.example.common.exception.GlobalException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

import static com.example.common.type.MessageType.PARAMETER_ERROR;
import static com.example.common.type.MessageType.SYSTEM_ERROR;

/**
 * 异常捕获
 *
 * @author 李磊
 * @since 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public ResultEntity catchGlobalException(GlobalException e) {
        ResultEntity entity = new ResultEntity();
        entity.setCode(e.getCode());
        entity.setMessage(e.getMessage());
        return entity;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResultEntity catchRuntimeException(RuntimeException e) {
        e.printStackTrace();
        ResultEntity entity = new ResultEntity();
        entity.setCode(SYSTEM_ERROR.getCode());
        entity.setMessage(e.getMessage());
        return entity;
    }

    /**
     * 校验controller方法实体参数
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultEntity paramException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        StringBuilder message = new StringBuilder(PARAMETER_ERROR.getMessage());
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            fieldErrors.forEach(error -> message.append(' ' + error.getField()
                    + ' ' + error.getDefaultMessage()));
        }
        ResultEntity entity = new ResultEntity();
        entity.setCode(PARAMETER_ERROR.getCode());
        entity.setMessage(message.toString());
        return entity;
    }

    /**
     * 校验controller方法非实体参数
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultEntity paramException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder(PARAMETER_ERROR.getMessage());
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        constraintViolations.forEach(item -> message.append(item.getMessage()));
        ResultEntity entity = new ResultEntity();
        entity.setCode(PARAMETER_ERROR.getCode());
        entity.setMessage(message.toString());
        return entity;
    }

    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResultEntity catchException(Exception e) {
        e.printStackTrace();
        ResultEntity entity = new ResultEntity();
        entity.setCode(SYSTEM_ERROR.getCode());
        entity.setMessage(SYSTEM_ERROR.getMessage());
        return entity;
    }
}