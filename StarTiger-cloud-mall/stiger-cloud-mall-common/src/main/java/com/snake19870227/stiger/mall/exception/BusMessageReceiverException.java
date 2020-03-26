package com.snake19870227.stiger.mall.exception;

import com.snake19870227.stiger.web.exception.BaseControllerException;

/**
 * @author Bu HuaYang
 */
public class BusMessageReceiverException extends BaseControllerException {

    public BusMessageReceiverException(String errorCode, Object... args) {
        super(errorCode, args);
    }

    public BusMessageReceiverException(String errorCode, Throwable cause, Object... args) {
        super(errorCode, cause, args);
    }

    public BusMessageReceiverException(Throwable cause) {
        super(cause);
    }
}
