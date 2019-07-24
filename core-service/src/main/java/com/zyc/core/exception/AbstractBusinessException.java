package com.zyc.core.exception;

import com.zyc.core.constant.enums.ResponseMessage;
import lombok.Getter;

/**
 * 业务异常
 *
 * @author zyc
 */
@Getter
public abstract class AbstractBusinessException extends RuntimeException {

    private static final long serialVersionUID = 6242200037685384883L;

    protected ResponseMessage responseMessage;

    AbstractBusinessException(ResponseMessage responseMessage) {
        super(responseMessage.getMessage());
        this.responseMessage = responseMessage;
    }

}
