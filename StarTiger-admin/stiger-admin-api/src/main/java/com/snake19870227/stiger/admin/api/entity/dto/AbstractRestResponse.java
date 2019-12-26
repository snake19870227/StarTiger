package com.snake19870227.stiger.admin.api.entity.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Bu HuaYang
 */
public abstract class AbstractRestResponse<T> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractRestResponse.class);

    public static final String DEFAULT_SUCCESS_RESP_CODE = "0000";
    public static final String DEFAULT_SUCCESS_RESP_MESSAGE = "成功";

    public static final String DEFAULT_FAILURE_RESP_CODE = "9999";
    public static final String DEFAULT_FAILURE_RESP_MESSAGE = "失败";

    private String respCode;

    private String respMessage;

    private T data;

    public AbstractRestResponse(String respCode, String respMessage) {
        this.respCode = respCode;
        this.respMessage = respMessage;
    }

    public AbstractRestResponse(String respCode, String respMessage, T data) {
        this.respCode = respCode;
        this.respMessage = respMessage;
        this.data = data;
    }

    public static DefaultRestResponse createSuccessRestResp(Object data) {
        return createRestResp(DEFAULT_SUCCESS_RESP_CODE, DEFAULT_SUCCESS_RESP_MESSAGE, data);
    }

    public static DefaultRestResponse createFailureRestResp(Object data) {
        return createRestResp(DEFAULT_FAILURE_RESP_CODE, DEFAULT_FAILURE_RESP_MESSAGE, data);
    }

    public static DefaultRestResponse createRestResp(String respCode, String respMessage, Object data) {
        try {
            return createRestResp(respCode, respMessage, data, DefaultRestResponse.class);
        } catch (Exception e) {
            logger.error("创建RestResponse失败", e);
            return new DefaultRestResponse(respCode, respMessage, data);
        }
    }

    public static <F, M extends AbstractRestResponse<F>> M createSuccessRestResp(F data, Class<M> clazz)
            throws IllegalAccessException, InstantiationException, InvocationTargetException {
        return createRestResp(DEFAULT_SUCCESS_RESP_CODE, DEFAULT_SUCCESS_RESP_MESSAGE, data, clazz);
    }

    public static <F, M extends AbstractRestResponse<F>> M createFailureRestResp(F data, Class<M> clazz)
            throws IllegalAccessException, InstantiationException, InvocationTargetException {
        return createRestResp(DEFAULT_FAILURE_RESP_CODE, DEFAULT_FAILURE_RESP_MESSAGE, data, clazz);
    }

    public static <F, M extends AbstractRestResponse<F>> M createRestResp(String respCode, String respMessage, F data, Class<M> clazz)
            throws IllegalAccessException, InstantiationException, InvocationTargetException {

        M restResp;

        Constructor<M> constructor1 = ClassUtils.getConstructorIfAvailable(clazz, String.class, String.class, data.getClass());
        Constructor<M> constructor2 = ClassUtils.getConstructorIfAvailable(clazz, String.class, String.class);

        if (constructor1 != null) {
            restResp = constructor1.newInstance(respCode, respMessage, data);
        } else if (constructor2 != null) {
            restResp = constructor2.newInstance(respCode, respMessage);
            restResp.setData(data);
        } else {
            restResp = clazz.newInstance();
            restResp.setRespCode(respCode);
            restResp.setRespMessage(respMessage);
            restResp.setData(data);
        }

        return restResp;
    }

    public static class DefaultRestResponse extends AbstractRestResponse<Object> {

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
