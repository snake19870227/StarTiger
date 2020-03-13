package com.snake19870227.stiger.web.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import com.snake19870227.stiger.web.utils.WebUtil;

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
        logger.error(ex.getLocalizedMessage(), ex);

        if (ex instanceof BaseMvcException) {
            BaseMvcException rhe = (BaseMvcException) ex;
            return rhe.buildMvcView();
        }

        String respCode = "9998";

        if (ex instanceof MethodArgumentNotValidException || ex instanceof BindException) {
            respCode = "9997";
        }

        boolean isResponseBody = false;
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            ResponseBody annotation = handlerMethod.getMethodAnnotation(ResponseBody.class);
            if (annotation == null) {
                Class<?> beanType = handlerMethod.getBeanType();
                annotation = AnnotatedElementUtils.findMergedAnnotation(beanType, ResponseBody.class);
            }
            if (annotation != null) {
                isResponseBody = true;
            }
        }

        if (WebUtil.isAjaxRequest(request) || isResponseBody) {
            return new RestHttpException(respCode, ex).buildMvcView();
        } else {
            return new NormalHttpException(respCode, ex).buildMvcView();
        }
    }
}
