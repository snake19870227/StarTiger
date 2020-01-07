package com.snake19870227.stiger.admin.api.security;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.ContentType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.admin.api.entity.dto.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

/**
 * @author Bu HuaYang
 */
public abstract class BaseSecurityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(BaseSecurityExceptionHandler.class);

    @Autowired
    private ObjectMapper objectMapper;

    protected void doHandle(HttpServletRequest request, HttpServletResponse response, Exception exception) throws JsonProcessingException {

        if (response.isCommitted()) {
            logger.warn("请求响应已被提交");
            return;
        }

        RestResponse<Object> restResponse = createRestResponse(request, response, exception);

        ServletUtil.write(
                response,
                objectMapper.writeValueAsString(restResponse),
                ContentType.build(MediaType.APPLICATION_JSON_VALUE, StandardCharsets.UTF_8)
        );
    }

    protected abstract RestResponse.DefaultRestResponse createRestResponse(HttpServletRequest request, HttpServletResponse response, Exception exception);
}
