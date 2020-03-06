package com.snake19870227.stiger.exception;

import cn.hutool.core.util.StrUtil;
import com.snake19870227.stiger.core.StarTigerConstant;
import com.snake19870227.stiger.context.StarTigerContext;

/**
 * @author Bu HuaYang
 */
public abstract class BaseRuntimeException extends RuntimeException {

    protected String errorCode;

    public BaseRuntimeException(String errorCode, Object... args) {
        super(StarTigerContext.getMessage(StarTigerConstant.StatusCode.PREFIX_CODE + errorCode, args));
        this.errorCode = errorCode;
    }

    public BaseRuntimeException(String errorCode, Throwable cause, Object... args) {
        super(StarTigerContext.getMessage(StarTigerConstant.StatusCode.PREFIX_CODE + errorCode, args), cause);
        this.errorCode = errorCode;
    }

    public BaseRuntimeException(String message) {
        super(message);
        this.errorCode = StarTigerConstant.StatusCode.CODE_9998;
    }

    public BaseRuntimeException(Throwable cause) {
        super(StarTigerContext.getMessage(StarTigerConstant.StatusCode.PREFIX_CODE + StarTigerConstant.StatusCode.CODE_9998, cause.toString()), cause);
        this.errorCode = StarTigerConstant.StatusCode.CODE_9998;
    }

    @Override
    public String getLocalizedMessage() {
        if (StrUtil.isNotBlank(errorCode)) {
            return "[" + errorCode + "]" + super.getLocalizedMessage();
        } else {
            return super.getLocalizedMessage();
        }
    }
}
