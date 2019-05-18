package com.zyc.core.model.base.vo;

import com.zyc.core.constant.enums.ResponseMessage;
import com.zyc.core.util.GsonUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;

/**
 * 统一请求响应结果
 *
 * @author zyc
 */
@Getter
@Setter
@ApiModel(description = "响应结果封装")
public class ResponseResult<T> {

    @ApiModelProperty("返回的数据")
    private T data;

    @ApiModelProperty("返回的状态码，默认成功")
    private int code = ResponseMessage.SUCCESS.getCode();

    @ApiModelProperty("对返回结果的描述")
    private String message;

    private ResponseResult(T data) {
        this.data = data;
    }

    private ResponseResult(String message, int code) {
        this.message = message;
        this.code = code;
    }

    private ResponseResult(T data, String message, int code) {
        this(message, code);
        this.data = data;
    }

    private ResponseResult(T data, ResponseMessage responseMessage) {
        this(data, responseMessage.getMessage(), responseMessage.getCode());
    }

    /**
     * 响应成功
     *
     * @param data 响应结果
     * @return 响应结果封装
     */
    public static <R> ResponseResult<R> success(R data) {
        return new ResponseResult<>(data);
    }

    /**
     * @param data            响应结果
     * @param responseMessage 响应结果描述
     * @param <R>             响应结果类型
     * @return 响应结果封装
     */
    public static <R> ResponseResult<R> success(R data, ResponseMessage responseMessage) {
        return new ResponseResult<>(data, responseMessage);
    }


    /**
     * 响应失败
     *
     * @param responseMessage 异常信息枚举
     * @return 响应结果封装
     * @see ResponseMessage
     */
    public static <R> ResponseResult<R> fail(ResponseMessage responseMessage) {
        return new ResponseResult<>(responseMessage.getMessage(),
                responseMessage.getCode());
    }

    /**
     * 响应失败
     *
     * @param data            响应结果
     * @param responseMessage 异常信息枚举
     * @return 响应结果封装
     * @see ResponseMessage
     */
    public static <R> ResponseResult<R> fail(R data, ResponseMessage responseMessage) {
        return new ResponseResult<>(data, responseMessage.getMessage(),
                responseMessage.getCode());
    }

    /**
     * 响应失败
     *
     * @param fieldError 验证不通过的Field
     * @return 响应结果封装
     * @see FieldError
     */
    public static <R> ResponseResult<R> fail(FieldError fieldError) {
        return new ResponseResult<>(fieldError.getDefaultMessage(),
                ResponseMessage.ILLEGAL_PARAMETER.getCode());
    }

    /**
     * 响应失败
     *
     * @param constraintViolation 违规约束
     * @return 响应结果封装
     * @see ConstraintViolation
     */
    public static <R> ResponseResult<R> fail(ConstraintViolation<?> constraintViolation) {
        return new ResponseResult<>(constraintViolation.getMessage(),
                ResponseMessage.ILLEGAL_PARAMETER.getCode());
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this);
    }
}
