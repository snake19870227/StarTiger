package com.snake19870227.stiger.admin.web.security;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.ContentType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.web.utils.WebUtil;
import com.snake19870227.stiger.admin.web.ProjectConstant;
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
            responseIfAjax("2001", response);
        } else {
            response.sendRedirect(ProjectConstant.UrlPath.LOGIN + "?" + ProjectConstant.UrlParamNames.LOGIN_EXPIRE);
        }
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        if (response.isCommitted()) {
            logger.warn("请求响应已被提交");
            return;
        }

        if (WebUtil.isAjaxRequest(request)) {
            responseIfAjax("2002", response);
        } else {
            response.sendRedirect(ProjectConstant.UrlPath.ACCESS_DENIED);
        }
    }

    private void responseIfAjax(String respCode, HttpServletResponse response) throws JsonProcessingException {

        RestResponse.DefaultRestResponse restResponse = RestResponseBuilder.createDefaultRestResp(false, respCode, null);

        ServletUtil.write(
                response,
                objectMapper.writeValueAsString(restResponse),
                ContentType.build(MediaType.APPLICATION_JSON_VALUE, StandardCharsets.UTF_8)
        );
    }
}
