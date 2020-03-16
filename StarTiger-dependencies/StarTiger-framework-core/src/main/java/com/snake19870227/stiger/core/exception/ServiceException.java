package com.snake19870227.stiger.core.exception;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/03/17
 */
public class ServiceException extends BaseRuntimeException {

    public ServiceException(String errorCode, Object... args) {
        super(errorCode, args);
    }

    public ServiceException(String errorCode, Throwable cause, Object... args) {
        super(errorCode, cause, args);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
