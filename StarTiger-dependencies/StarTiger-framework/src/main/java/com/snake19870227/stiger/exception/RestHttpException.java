package com.snake19870227.stiger.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bu HuaYang
 */
public class RestHttpException extends BaseResponseBodyException {

    public RestHttpException(String errorCode, Object... args) {
        super(errorCode, args);
    }

    public RestHttpException(String errorCode, Throwable cause, Object... args) {
        super(errorCode, cause, args);
    }

    public RestHttpException(String message) {
        super(message);
    }

    public RestHttpException(Throwable cause) {
        super(cause);
    }

    @Override
    protected Map<String, ?> buildModelMap() {
        Map<String, Object> modelMap = new HashMap<>(2);
        modelMap.put("respCode", errorCode);
        modelMap.put("respMessage", getLocalizedMessage());
        return modelMap;
    }
}
