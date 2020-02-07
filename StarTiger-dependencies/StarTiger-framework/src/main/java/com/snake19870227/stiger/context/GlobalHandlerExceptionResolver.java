package com.snake19870227.stiger.context;

import com.snake19870227.stiger.exception.RestHttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Bu HuaYang
 */
public class GlobalHandlerExceptionResolver extends AbstractHandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(GlobalHandlerExceptionResolver.class);

    public GlobalHandlerExceptionResolver() {
        logger.debug("创建全局异常处理器");
    }

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
