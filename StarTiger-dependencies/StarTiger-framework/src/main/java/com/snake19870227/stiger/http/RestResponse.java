package com.snake19870227.stiger.http;

import cn.hutool.core.util.ObjectUtil;
import com.snake19870227.stiger.context.StarTigerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static com.snake19870227.stiger.StarTigerConstant.StatusCode;

/**
 * @author Bu HuaYang
 */
public class RestResponse<T> {

    private String respCode;

    private String respMessage;

    private T data;

    public RestResponse() {
    }

    public RestResponse(String respCode, String respMessage) {
        this.respCode = respCode;
        this.respMessage = respMessage;
    }

    public RestResponse(String respCode, String respMessage, T data) {
        this.respCode = respCode;
        this.respMessage = respMessage;
        this.data = data;
    }

    public static class DefaultRestResponse extends RestResponse<Object> {

        public DefaultRestResponse(String respCode, String respMessage) {
            super(respCode, respMessage);
        }

        public DefaultRestResponse(String respCode, String respMessage, Object data) {
            super(respCode, respMessage, data);
        }
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMessage() {
        return respMessage;
    }

    public void setRespMessage(String respMessage) {
        this.respMessage = respMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
