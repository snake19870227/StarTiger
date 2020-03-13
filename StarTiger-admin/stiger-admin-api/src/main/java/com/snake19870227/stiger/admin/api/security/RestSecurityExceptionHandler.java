package com.snake19870227.stiger.admin.api.security;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.ContentType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.core.restful.RestResponse;
import com.snake19870227.stiger.core.restful.RestResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Bu HuaYang
 */
public class RestSecurityExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestSecurityExceptionHandler.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        if (response.isCommitted()) {
            logger.warn("请求响应已被提交");
            return;
        }

        RestResponse.DefaultRestResponse restResponse = RestResponseBuilder.createRestResp(false, "2001", null);

        ServletUtil.write(response, objectMapper.writeValueAsString(restResponse), ContentType.build(MediaType.APPLICATION_JSON_VALUE, StandardCharsets.UTF_8));
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        if (response.isCommitted()) {
            logger.warn("请求响应已被提交");
            return;
        }

        RestResponse.DefaultRestResponse restResponse = RestResponseBuilder.createRestResp(false, "2002", null);

        ServletUtil.write(response, objectMapper.writeValueAsString(restResponse), ContentType.build(MediaType.APPLICATION_JSON_VALUE, StandardCharsets.UTF_8));
    }
}
