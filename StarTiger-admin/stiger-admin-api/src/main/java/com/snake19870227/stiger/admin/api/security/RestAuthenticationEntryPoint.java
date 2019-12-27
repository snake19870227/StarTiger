package com.snake19870227.stiger.admin.api.security;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.ContentType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.admin.api.ProjectConstant;
import com.snake19870227.stiger.admin.api.entity.dto.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Bu HuaYang
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        if (response.isCommitted()) {
            logger.warn("请求响应已被提交");
            return;
        }

        RestResponse.DefaultRestResponse restResponse
                = RestResponse.createRestResp(ProjectConstant.RestResp.Code2001.CODE, ProjectConstant.RestResp.Code2001.MESSAGE, null);

        ServletUtil.write(response, objectMapper.writeValueAsString(restResponse), ContentType.build(MediaType.APPLICATION_JSON_VALUE, StandardCharsets.UTF_8));

    }
}
