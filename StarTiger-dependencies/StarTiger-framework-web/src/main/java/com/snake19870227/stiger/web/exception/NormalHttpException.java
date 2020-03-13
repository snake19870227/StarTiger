package com.snake19870227.stiger.web.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

/**
 * @author Bu HuaYang
 */
public class NormalHttpException extends BaseMvcException {

    public NormalHttpException(String errorCode, Object... args) {
        super(errorCode, args);
    }

    public NormalHttpException(String errorCode, Throwable cause, Object... args) {
        super(errorCode, cause, args);
    }

    public NormalHttpException(String message) {
        super(message);
    }

    public NormalHttpException(Throwable cause) {
        super(cause);
    }

    @Override
    public ModelAndView buildMvcView() {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("errorMessage", this.getLocalizedMessage());
        return new ModelAndView("error", modelMap);
    }
}
