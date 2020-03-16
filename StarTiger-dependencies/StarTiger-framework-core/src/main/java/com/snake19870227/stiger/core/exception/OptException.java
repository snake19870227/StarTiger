package com.snake19870227.stiger.core.exception;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/03/17
 */
public class OptException extends BaseRuntimeException {

    public OptException(String errorCode, Object... args) {
        super(errorCode, args);
    }

    public OptException(String errorCode, Throwable cause, Object... args) {
        super(errorCode, cause, args);
    }

    public OptException(String message) {
        super(message);
    }

    public OptException(Throwable cause) {
        super(cause);
    }
}
