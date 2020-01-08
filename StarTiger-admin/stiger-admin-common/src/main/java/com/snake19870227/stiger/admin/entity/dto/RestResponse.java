package com.snake19870227.stiger.admin.entity.dto;

import cn.hutool.core.util.ObjectUtil;
import com.snake19870227.stiger.admin.ProjectConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Bu HuaYang
 */
public class RestResponse<T> {

    private static final Logger logger = LoggerFactory.getLogger(RestResponse.class);

    public static final String DEFAULT_SUCCESS_RESP_CODE = ProjectConstant.RestResp.Success.CODE;
    public static final String DEFAULT_SUCCESS_RESP_MESSAGE = ProjectConstant.RestResp.Success.MESSAGE;

    public static final String DEFAULT_FAILURE_RESP_CODE = ProjectConstant.RestResp.Failure.CODE;
    public static final String DEFAULT_FAILURE_RESP_MESSAGE = ProjectConstant.RestResp.Failure.MESSAGE;

    private String respCode;

    private String respMessage;

    private T data;

    public RestResponse(String respCode, String respMessage) {
        this.respCode = respCode;
        this.respMessage = respMessage;
    }

    public RestResponse(String respCode, String respMessage, T data) {
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

    public static <F, M extends RestResponse<F>> M createSuccessRestResp(F data, Class<M> clazz)
            throws IllegalAccessException, InstantiationException, InvocationTargetException {
        return createRestResp(DEFAULT_SUCCESS_RESP_CODE, DEFAULT_SUCCESS_RESP_MESSAGE, data, clazz);
    }

    public static <F, M extends RestResponse<F>> M createFailureRestResp(F data, Class<M> clazz)
            throws IllegalAccessException, InstantiationException, InvocationTargetException {
        return createRestResp(DEFAULT_FAILURE_RESP_CODE, DEFAULT_FAILURE_RESP_MESSAGE, data, clazz);
    }

    public static <F, M extends RestResponse<F>> M createRestResp(String respCode, String respMessage, F data, Class<M> clazz)
            throws IllegalAccessException, InstantiationException, InvocationTargetException {

        M restResp;

        Constructor<M> constructor1 = null;
        if (ObjectUtil.isNotNull(data)) {
            constructor1 = ClassUtils.getConstructorIfAvailable(clazz, String.class, String.class, data.getClass());
        }

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