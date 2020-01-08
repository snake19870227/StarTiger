package com.snake19870227.stiger.admin.api.security;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.ContentType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.nio.charset.StandardCharsets;

import static com.snake19870227.stiger.admin.entity.dto.RestResponse.*;

/**
 * @author Bu HuaYang
 */
public abstract class BaseRestAuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(BaseRestAuthenticationSuccessHandler.class);

    @Autowired
    private ObjectMapper objectMapper;

    protected void doHandler(HttpServletRequest request, HttpServletResponse response, Object object) throws JsonProcessingException {

        if (response.isCommitted()) {
            logger.warn("请求响应已被提交");
            return;
        }

        DefaultRestResponse restResponse = buildResponse(request, response, object);

        ServletUtil.write(response, objectMapper.writeValueAsString(restResponse), ContentType.build(MediaType.APPLICATION_JSON_VALUE, StandardCharsets.UTF_8));
    }

    protected abstract DefaultRestResponse buildResponse(HttpServletRequest request, HttpServletResponse response, Object object);
}
