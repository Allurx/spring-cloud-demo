package com.zyc.core.constant.enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * 响应信息
 *
 * @author zyc
 */
@Getter
@ApiModel(description = "响应信息")
public enum ResponseMessage {

    /**
     * 系统
     */
    SUCCESS(0, "成功"),
    INTERNAL_SERVER_ERROR(-1, "系统繁忙，请稍后再试"),


    /**
     * 用户
     */
    MISSING_USER(1000, "缺失用户信息"),


    /**
     * 通用
     */
    ILLEGAL_PARAMETER(3000, "非法参数"),

    ;

    @ApiModelProperty("异常错误码")
    private int code;

    @ApiModelProperty("异常信息")
    private String message;

    ResponseMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
