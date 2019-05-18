package com.zyc.core.exception;

import com.zyc.core.constant.enums.ResponseMessage;
import io.swagger.annotations.ApiModel;
import lombok.Getter;

/**
 * @author zyc
 */
@Getter
@ApiModel(description = "业务异常")
public abstract class AbstractBusinessException extends RuntimeException {

    private static final long serialVersionUID = 6242200037685384883L;

    protected ResponseMessage responseMessage;

    public AbstractBusinessException() {
        this(ResponseMessage.INTERNAL_SERVER_ERROR);
    }

    AbstractBusinessException(ResponseMessage responseMessage) {
        super(responseMessage.getMessage());
        this.responseMessage = responseMessage;
    }

}
