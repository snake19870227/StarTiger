package com.snake19870227.stiger.admin.api.config;

import com.snake19870227.stiger.web.exception.RestHttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Bu HuaYang
 */
@Component
public class GlobalHandlerExceptionResolver extends AbstractHandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(GlobalHandlerExceptionResolver.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response,
                                              Object handler,
                                              Exception ex) {

        if (ex instanceof RestHttpException) {
            RestHttpException rhe = (RestHttpException) ex;
            return rhe.buildMvcView();
        } else {
            return new RestHttpException(ex).buildMvcView();
        }
    }
}
