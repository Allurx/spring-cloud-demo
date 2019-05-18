package com.zyc.core.handler;


import com.zyc.core.constant.enums.ResponseMessage;
import com.zyc.core.exception.AbstractBusinessException;
import com.zyc.core.model.base.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

/**
 * @author zyc
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseResult<?> exception(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ResponseResult.fail(ResponseMessage.INTERNAL_SERVER_ERROR);
    }

    /**
     * 反序列化时抛出的自定义异常会被嵌套到 {@code HttpMessageNotReadableException}中
     *
     * @param exception {@link HttpMessageNotReadableException}
     * @return {@link ResponseResult}
     * @see HttpMessageNotReadableException
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseResult<?> exception(HttpMessageNotReadableException exception) {
        if (exception.contains(AbstractBusinessException.class)) {
            return businessException((AbstractBusinessException) exception.getMostSpecificCause());
        }
        log.error(exception.getMessage(), exception);
        return ResponseResult.fail(ResponseMessage.INTERNAL_SERVER_ERROR);
    }

    /**
     * 捕获 @Valid 注解异常
     *
     * @param exception {@link MethodArgumentNotValidException}
     * @return {@link ResponseResult}
     * @see Valid
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult<?> validationException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        FieldError error = bindingResult.getFieldError();
        log.error(exception.getMessage(), exception);
        return ResponseResult.fail(error);
    }

    @ExceptionHandler(BindException.class)
    public ResponseResult<?> validationException(BindException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        FieldError error = bindingResult.getFieldError();
        log.error(exception.getMessage(), exception);
        return ResponseResult.fail(error);
    }

    /**
     * 捕获 @Validated 注解异常
     *
     * @param exception {@link ConstraintViolationException}
     * @return {@link ResponseResult}
     * @see Validated
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseResult<?> validationException(ConstraintViolationException exception) {
        ConstraintViolation<?> constraintViolation = exception.getConstraintViolations().iterator().next();
        log.error(constraintViolation.getMessage(), exception);
        return ResponseResult.fail(constraintViolation);
    }

    /**
     * 业务相关异常
     *
     * @param exception {@link AbstractBusinessException}
     * @return {@link ResponseResult}
     */
    @ExceptionHandler(AbstractBusinessException.class)
    public ResponseResult<?> businessException(AbstractBusinessException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseResult.fail(exception.getResponseMessage());
    }


}
