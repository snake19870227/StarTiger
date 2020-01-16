package com.snake19870227.stiger.admin.web.config;

import com.snake19870227.stiger.admin.SuperConstant;
import com.snake19870227.stiger.admin.exception.BaseMvcException;
import com.snake19870227.stiger.admin.exception.BaseRuntimeException;
import com.snake19870227.stiger.admin.utils.WebUtil;
import com.snake19870227.stiger.admin.web.exception.AjaxException;
import com.snake19870227.stiger.admin.web.exception.NormalHttpException;
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
            return new AjaxException(ex).buildMvcView();
        } else {
            return new NormalHttpException(ex).buildMvcView();
        }
    }
}
