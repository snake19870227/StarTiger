package com.snake19870227.stiger.admin.api.security;

import com.snake19870227.stiger.admin.ProjectConstant;
import com.snake19870227.stiger.admin.entity.dto.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Bu HuaYang
 */
public class RestAccessDeniedHandler extends BaseSecurityExceptionHandler implements AccessDeniedHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        doHandle(request, response, accessDeniedException);

    }

    @Override
    protected RestResponse.DefaultRestResponse createRestResponse(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        return RestResponse.createRestResp(ProjectConstant.RestResp.Code2002.CODE, ProjectConstant.RestResp.Code2002.MESSAGE, null);
    }
}
