package com.example.common.handler;

import com.example.common.entity.ResultEntity;
import com.example.common.exception.GlobalException;
import com.example.common.exception.MessageType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

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
        return new ResultEntity() {{
            setCode(e.getCode());
            setMessage(e.getMessage());
        }};
    }

    @ExceptionHandler(RuntimeException.class)
    public ResultEntity catchRuntimeException(RuntimeException e) {
        e.printStackTrace();
        return new ResultEntity() {{
            setCode(MessageType.SYSTEM_ERROR.getCode());
            setMessage(MessageType.SYSTEM_ERROR.getMessage());
        }};
    }

    /**
     * 校验controller方法实体参数
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultEntity paramException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        StringBuilder message = new StringBuilder(MessageType.PARAMETER_ERROR.getMessage());
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            fieldErrors.forEach(error -> message.append(' ' + error.getField()
                    + "->" + error.getDefaultMessage()));
        }
        return new ResultEntity() {{
            setCode(MessageType.PARAMETER_ERROR.getCode());
            setMessage(message.toString());
        }};
    }

    /**
     * 校验controller方法非实体参数
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultEntity paramException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder(MessageType.PARAMETER_ERROR.getMessage());
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        constraintViolations.forEach(item -> message.append(item.getMessage()));
        return new ResultEntity() {{
            setCode(MessageType.PARAMETER_ERROR.getCode());
            setMessage(message.toString());
        }};
    }

    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResultEntity catchException(Exception e) {
        e.printStackTrace();
        return new ResultEntity() {{
            setCode(MessageType.SYSTEM_ERROR.getCode());
            setMessage(MessageType.SYSTEM_ERROR.getMessage());
        }};
    }
}