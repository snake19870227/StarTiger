package com.snake19870227.stiger.admin.web.exception;

import com.snake19870227.stiger.admin.exception.BaseResponseBodyException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bu HuaYang
 */
public class AjaxHttpException extends BaseResponseBodyException {

    public AjaxHttpException(String errorCode, Object... args) {
        super(errorCode, args);
    }

    public AjaxHttpException(String errorCode, Throwable cause, Object... args) {
        super(errorCode, cause, args);
    }

    public AjaxHttpException(String message) {
        super(message);
    }

    public AjaxHttpException(Throwable cause) {
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
