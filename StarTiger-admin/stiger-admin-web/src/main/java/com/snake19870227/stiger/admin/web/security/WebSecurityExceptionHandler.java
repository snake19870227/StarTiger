package com.snake19870227.stiger.admin.web.security;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.ContentType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.admin.entity.dto.RestResponse;
import com.snake19870227.stiger.admin.utils.WebUtil;
import com.snake19870227.stiger.admin.web.ProjectConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Bu HuaYang
 */
public class WebSecurityExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

    private static final Logger logger = LoggerFactory.getLogger(WebSecurityExceptionHandler.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        if (response.isCommitted()) {
            logger.warn("请求响应已被提交");
            return;
        }

        if (WebUtil.isAjaxRequest(request)) {
            responseIfAjax("code.2001", response);
        } else {
            response.sendRedirect("/login?" + ProjectConstant.UrlParamNames.LOGIN_EXPIRE);
//            response.sendRedirect("/login");
        }
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        if (response.isCommitted()) {
            logger.warn("请求响应已被提交");
            return;
        }

        if (WebUtil.isAjaxRequest(request)) {
            responseIfAjax("code.2002", response);
        } else {
//            response.sendRedirect("/accessDenied");
            ServletUtil.write(
                    response,
                    "accessDenied",
                    ContentType.build(MediaType.APPLICATION_JSON_VALUE, StandardCharsets.UTF_8)
            );
        }
    }

    private void responseIfAjax(String respCode, HttpServletResponse response) throws JsonProcessingException {

        RestResponse.DefaultRestResponse restResponse = RestResponse.createRestResp(false, respCode, null);

        ServletUtil.write(
                response,
                objectMapper.writeValueAsString(restResponse),
                ContentType.build(MediaType.APPLICATION_JSON_VALUE, StandardCharsets.UTF_8)
        );
    }
}
