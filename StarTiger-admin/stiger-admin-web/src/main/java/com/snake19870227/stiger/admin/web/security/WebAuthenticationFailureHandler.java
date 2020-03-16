package com.snake19870227.stiger.admin.web.security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import com.snake19870227.stiger.admin.web.ProjectConstant;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/03/16
 */
public class WebAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final Logger logger = LoggerFactory.getLogger(WebAuthenticationFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.error("登录失败[{}]", exception.getLocalizedMessage(), exception);
        if (exception instanceof CredentialsExpiredException) {
            response.sendRedirect(ProjectConstant.UrlPath.LOGIN + "?" + ProjectConstant.UrlParamNames.LOGIN_EXPIRE);
        } else {
            response.sendRedirect(ProjectConstant.UrlPath.LOGIN + "?" + ProjectConstant.UrlParamNames.LOGIN_ERROR);
        }
    }
}
