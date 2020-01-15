package com.snake19870227.stiger.admin.exception;

/**
 * @author Bu HuaYang
 */
public class AjaxException extends BaseRuntimeException {

    public AjaxException(String errorCode, Object... args) {
        super(errorCode, args);
    }
}
