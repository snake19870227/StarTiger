package com.snake19870227.stiger.web.exception;

import com.snake19870227.stiger.core.exception.BaseRuntimeException;

/**
 * @author Bu HuaYang
 */
public class MvcException extends BaseRuntimeException {

    public MvcException(String errorCode, Object... args) {
        super(errorCode, args);
    }

    public MvcException(String errorCode, Throwable cause, Object... args) {
        super(errorCode, cause, args);
    }

    public MvcException(String message) {
        super(message);
    }

    public MvcException(Throwable cause) {
        super(cause);
    }
}
