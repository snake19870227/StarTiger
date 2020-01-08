package com.snake19870227.stiger.admin.api.security;

import com.snake19870227.stiger.admin.ProjectConstant;
import com.snake19870227.stiger.admin.entity.dto.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Bu HuaYang
 */
public class RestAuthenticationEntryPoint extends BaseSecurityExceptionHandler implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        doHandle(request, response, authException);

    }

    @Override
    protected RestResponse.DefaultRestResponse createRestResponse(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        return RestResponse.createRestResp(ProjectConstant.RestResp.Code2001.CODE, ProjectConstant.RestResp.Code2001.MESSAGE, null);
    }
}
