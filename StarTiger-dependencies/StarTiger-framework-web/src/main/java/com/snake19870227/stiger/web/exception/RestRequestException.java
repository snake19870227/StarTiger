package com.snake19870227.stiger.web.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Bu HuaYang
 */
public class RestRequestException extends MvcException {

    private static final Logger logger = LoggerFactory.getLogger(RestRequestException.class);

    public RestRequestException(String errorCode, Object... args) {
        super(errorCode, args);
    }

    public RestRequestException(String errorCode, Throwable cause, Object... args) {
        super(errorCode, cause, args);
    }

    public RestRequestException(String message) {
        super(message);
    }

    public RestRequestException(Throwable cause) {
        super(cause);
    }
}
