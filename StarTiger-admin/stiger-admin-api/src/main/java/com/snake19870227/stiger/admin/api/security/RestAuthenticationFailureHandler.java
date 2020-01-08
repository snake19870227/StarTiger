package com.snake19870227.stiger.admin.api.security;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.ContentType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.admin.ProjectConstant.RestResp.*;
import com.snake19870227.stiger.admin.entity.dto.RestResponse;
import com.snake19870227.stiger.admin.entity.dto.RestResponse.DefaultRestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Bu HuaYang
 */
public class RestAuthenticationFailureHandler extends BaseAuthenticationSuccessHandler implements AuthenticationFailureHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {

        doHandler(request, response, exception);

    }

    @Override
    protected DefaultRestResponse buildResponse(HttpServletRequest request, HttpServletResponse response, Object object) {
        return RestResponse.createRestResp(false, "code.1001", null);
    }
}
