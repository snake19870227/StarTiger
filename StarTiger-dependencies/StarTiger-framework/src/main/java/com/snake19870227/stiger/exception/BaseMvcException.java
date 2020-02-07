package com.snake19870227.stiger.exception;

import org.springframework.web.servlet.ModelAndView;

/**
 * @author Bu HuaYang
 */
public abstract class BaseMvcException extends BaseRuntimeException {

    public BaseMvcException(String errorCode, Object... args) {
        super(errorCode, args);
    }

    public BaseMvcException(String errorCode, Throwable cause, Object... args) {
        super(errorCode, cause, args);
    }

    public BaseMvcException(String message) {
        super(message);
    }

    public BaseMvcException(Throwable cause) {
        super(cause);
    }

    public abstract ModelAndView buildMvcView();
}
