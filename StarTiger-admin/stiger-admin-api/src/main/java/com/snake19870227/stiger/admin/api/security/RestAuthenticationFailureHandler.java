package com.snake19870227.stiger.admin.api.security;

import com.snake19870227.stiger.admin.entity.dto.RestResponse;
import com.snake19870227.stiger.admin.entity.dto.RestResponse.DefaultRestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Bu HuaYang
 */
public class RestAuthenticationFailureHandler extends BaseRestAuthenticationSuccessHandler implements AuthenticationFailureHandler {

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
