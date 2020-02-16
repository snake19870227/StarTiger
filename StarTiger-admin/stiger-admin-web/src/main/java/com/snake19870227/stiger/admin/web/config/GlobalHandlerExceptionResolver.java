package com.snake19870227.stiger.admin.web.config;

import com.snake19870227.stiger.admin.utils.WebUtil;
import com.snake19870227.stiger.admin.web.exception.AjaxHttpException;
import com.snake19870227.stiger.admin.web.exception.NormalHttpException;
import com.snake19870227.stiger.exception.BaseMvcException;
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

        if (ex instanceof BaseMvcException) {
            BaseMvcException bme = (BaseMvcException) ex;
            return bme.buildMvcView();
        } else if (WebUtil.isAjaxRequest(request)) {
            return new AjaxHttpException(ex).buildMvcView();
        } else {
            return new NormalHttpException(ex).buildMvcView();
        }
    }
}
