package com.snake19870227.stiger.core.restful;

import cn.hutool.core.util.ObjectUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;
import com.snake19870227.stiger.core.StarTigerConstant;
import com.snake19870227.stiger.core.StarTigerContext;

/**
 * @author Bu HuaYang
 */
public class RestResponseBuilder {

    private static final Logger logger = LoggerFactory.getLogger(RestResponseBuilder.class);

    public static final String DEFAULT_SUCCESS_RESP_CODE = StarTigerConstant.StatusCode.CODE_0000;

    public static final String DEFAULT_FAILURE_RESP_CODE = StarTigerConstant.StatusCode.CODE_9999;

    public static RestResponse.DefaultRestResponse createSuccessRestResp() {
        return createRestResp(true, DEFAULT_SUCCESS_RESP_CODE, null);
    }

    public static RestResponse.DefaultRestResponse createFailureRestResp() {
        return createRestResp(false, DEFAULT_FAILURE_RESP_CODE, null);
    }

    public static RestResponse.DefaultRestResponse createSuccessRestResp(Object data) {
        return createRestResp(true, DEFAULT_SUCCESS_RESP_CODE, data);
    }

    public static RestResponse.DefaultRestResponse createFailureRestResp(Object data) {
        return createRestResp(false, DEFAULT_FAILURE_RESP_CODE, data);
    }

    public static RestResponse.DefaultRestResponse createRestResp(boolean isSuccess, String respCode, Object data) {

        String respMessage = null;
        try {
            respMessage = buildMessage(respCode);
        } catch (Exception e) {
            logger.warn("未找到国际化文本配置[{}]", respCode, e);
            respCode = isSuccess ? DEFAULT_SUCCESS_RESP_CODE : DEFAULT_FAILURE_RESP_CODE;
            respMessage = buildMessage(respCode);
        }

        return new RestResponse.DefaultRestResponse(respCode, respMessage, data);
    }

    public static <F, M extends RestResponse<F>> M createSuccessRestResp(F data, Class<M> clazz) {
        return createRestResp(DEFAULT_SUCCESS_RESP_CODE, buildMessage(DEFAULT_SUCCESS_RESP_CODE), data, clazz);
    }

    public static <F, M extends RestResponse<F>> M createFailureRestResp(F data, Class<M> clazz) {
        return createRestResp(DEFAULT_FAILURE_RESP_CODE, buildMessage(DEFAULT_FAILURE_RESP_CODE), data, clazz);
    }

    public static <F, M extends RestResponse<F>> M createRestResp(String respCode, F data, Class<M> clazz) {
        return createRestResp(respCode, buildMessage(respCode), data, clazz);
    }

    public static <F, M extends RestResponse<F>> M createRestResp(String respCode, String respMessage, F data, Class<M> clazz) {

        M restResp;

        Constructor<M> constructor1 = null;
        if (ObjectUtil.isNotNull(data)) {
            constructor1 = ClassUtils.getConstructorIfAvailable(clazz, String.class, String.class, data.getClass());
        }

        Constructor<M> constructor2 = ClassUtils.getConstructorIfAvailable(clazz, String.class, String.class);

        try {
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
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            logger.error("创建RestResponse失败", e);
        }

        return null;
    }

    private static String buildMessage(String code, Object... args) {
        return StarTigerContext.getMessage(StarTigerConstant.StatusCode.PREFIX_CODE + code, args);
    }
}
