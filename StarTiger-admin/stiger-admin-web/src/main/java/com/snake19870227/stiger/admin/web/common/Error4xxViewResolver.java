package com.snake19870227.stiger.admin.web.common;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import com.snake19870227.stiger.web.StarTigerWebConstant;
import com.snake19870227.stiger.web.exception.PostWebErrorHandler;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/03/21
 */
public class Error4xxViewResolver implements ErrorViewResolver {

    private static final Logger logger = LoggerFactory.getLogger(Error4xxViewResolver.class);

    private PostWebErrorHandler postWebErrorHandler;

    public Error4xxViewResolver(ObjectProvider<PostWebErrorHandler> postExceptionHandlerProvider) {
        this.postWebErrorHandler = postExceptionHandlerProvider.getIfAvailable();
        logger.debug("创建4xx错误页面处理器");
    }

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        ModelAndView mv = null;
        if (HttpStatus.NOT_FOUND.value() == status.value()) {
            mv = new ModelAndView(StarTigerWebConstant.ViewName.ERROR_404, model);
        }
        if (HttpStatus.BAD_REQUEST.value() == status.value()) {
            mv = new ModelAndView(StarTigerWebConstant.ViewName.ERROR_400, model);
        }
        if (postWebErrorHandler != null) {
            postWebErrorHandler.errorPageHandler(request, status, mv);
        }
        return mv;
    }
}
