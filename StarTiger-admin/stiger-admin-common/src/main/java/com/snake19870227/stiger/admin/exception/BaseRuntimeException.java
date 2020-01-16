package com.snake19870227.stiger.admin.exception;

import com.snake19870227.stiger.admin.SuperConstant;
import com.snake19870227.stiger.admin.project.SuperContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Bu HuaYang
 */
public abstract class BaseRuntimeException extends RuntimeException {

    protected String errorCode;

    public BaseRuntimeException(String errorCode, Object... args) {
        super(SuperContext.getMessage(SuperConstant.StatusCode.PREFIX_CODE + errorCode, args));
        this.errorCode = errorCode;
    }

    public BaseRuntimeException(String errorCode, Throwable cause, Object... args) {
        super(SuperContext.getMessage(SuperConstant.StatusCode.PREFIX_CODE + errorCode, args), cause);
        this.errorCode = errorCode;
    }

    public BaseRuntimeException(String message) {
        super(message);
        this.errorCode = SuperConstant.StatusCode.CODE_9998;
    }

    public BaseRuntimeException(Throwable cause) {
        super(SuperContext.getMessage(SuperConstant.StatusCode.PREFIX_CODE + SuperConstant.StatusCode.CODE_9998, cause.toString()), cause);
        this.errorCode = SuperConstant.StatusCode.CODE_9998;
    }

    @Override
    public String getLocalizedMessage() {
        if (StringUtils.isNotBlank(errorCode)) {
            return "[" + errorCode + "]" + super.getLocalizedMessage();
        } else {
            return super.getLocalizedMessage();
        }
    }
}
