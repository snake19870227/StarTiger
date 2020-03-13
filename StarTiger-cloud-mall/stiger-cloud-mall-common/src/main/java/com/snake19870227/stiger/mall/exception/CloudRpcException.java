package com.snake19870227.stiger.mall.exception;

import com.snake19870227.stiger.web.exception.RestHttpException;

/**
 * @author Bu HuaYang
 */
public class CloudRpcException extends RestHttpException {

    public CloudRpcException(String errorCode, Object... args) {
        super(errorCode, args);
    }

    public CloudRpcException(String errorCode, Throwable cause, Object... args) {
        super(errorCode, cause, args);
    }
}
