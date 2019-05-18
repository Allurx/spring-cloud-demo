package com.zyc.core.constant.enums;

import lombok.Getter;

/**
 * @author zyc
 */
@Getter
public enum ResponseStatus {

    /**
     * 成功
     */
    RESPONSE_SUCCESS(true),

    /**
     * 失败
     */
    RESPONSE_FAIL(false);

    boolean status;

    ResponseStatus(boolean status) {
        this.status = status;
    }

}
