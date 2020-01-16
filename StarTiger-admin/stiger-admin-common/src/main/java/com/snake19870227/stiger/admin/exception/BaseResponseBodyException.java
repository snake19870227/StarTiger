package com.snake19870227.stiger.admin.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.admin.project.SuperContext;
import com.snake19870227.stiger.admin.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Map;

/**
 * @author Bu HuaYang
 */
public abstract class BaseResponseBodyException extends BaseMvcException {

    private static final Logger logger = LoggerFactory.getLogger(BaseResponseBodyException.class);

    public BaseResponseBodyException(String errorCode, Object... args) {
        super(errorCode, args);
    }

    public BaseResponseBodyException(String errorCode, Throwable cause, Object... args) {
        super(errorCode, cause, args);
    }

    public BaseResponseBodyException(String message) {
        super(message);
    }

    public BaseResponseBodyException(Throwable cause) {
        super(cause);
    }

    @Override
    public ModelAndView buildMvcView() {
        ObjectMapper objectMapper = loadJacksonObjectMapper();
        MappingJackson2JsonView view = new MappingJackson2JsonView(objectMapper);
        return new ModelAndView(view, buildModelMap());
    }

    protected ObjectMapper loadJacksonObjectMapper() {
        ObjectMapper objectMapper = null;
        try {
            objectMapper = SuperContext.getBean(ObjectMapper.class);
        } catch (BeansException e) {
            logger.warn("无法从 SpringContext 中获取 Jackson ObjectMapper", e);
        }
        if (objectMapper == null) {
            objectMapper = JsonUtil.buildJacksonObjectMapper();
        }
        return objectMapper;
    }

    protected abstract Map<String, ?> buildModelMap();
}
