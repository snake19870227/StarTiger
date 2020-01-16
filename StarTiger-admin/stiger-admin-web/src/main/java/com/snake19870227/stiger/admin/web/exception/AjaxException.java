package com.snake19870227.stiger.admin.web.exception;

import com.snake19870227.stiger.admin.exception.BaseRestHttpException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bu HuaYang
 */
public class AjaxException extends BaseRestHttpException {

    public AjaxException(String errorCode, Object... args) {
        super(errorCode, args);
    }

    public AjaxException(String errorCode, Throwable cause, Object... args) {
        super(errorCode, cause, args);
    }

    public AjaxException(String message) {
        super(message);
    }

    public AjaxException(Throwable cause) {
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
