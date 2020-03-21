package com.snake19870227.stiger.web.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import com.snake19870227.stiger.core.StarTigerConstant;
import com.snake19870227.stiger.core.exception.BaseRuntimeException;
import com.snake19870227.stiger.core.restful.RestResponse;
import com.snake19870227.stiger.core.restful.RestResponseBuilder;
import com.snake19870227.stiger.web.StarTigerWebConstant;
import com.snake19870227.stiger.web.utils.WebUtil;
import com.snake19870227.stiger.web.view.ModelAndViewBuilder;

/**
 * @author Bu HuaYang
 */
public class GlobalHandlerExceptionResolver extends AbstractHandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(GlobalHandlerExceptionResolver.class);

    private PostWebErrorHandler postWebErrorHandler;

    public GlobalHandlerExceptionResolver(ObjectProvider<PostWebErrorHandler> postExceptionHandlerProvider) {
        this.postWebErrorHandler = postExceptionHandlerProvider.getIfAvailable();
        logger.debug("创建全局异常处理器");
    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response,
                                              Object handler,
                                              Exception ex) {
        logger.error(ex.getLocalizedMessage(), ex);

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

        ModelAndView mv = null;
        if (WebUtil.isAjaxRequest(request) || isResponseBody) {
            RestResponse.DefaultRestResponse restResponse = RestResponseBuilder.createFailureDefaultRestResp(ex, null);
            mv = ModelAndViewBuilder.buildToJsonResponseBody(restResponse);
        } else {
            Map<String, Object> modelMap = new HashMap<>(1);
            String errorMessage;
            if (ex instanceof BaseRuntimeException) {
                errorMessage = ex.getLocalizedMessage();
            } else {
                errorMessage = "[" + StarTigerConstant.StatusCode.CODE_9998 + "]" + ex.getLocalizedMessage();
            }
            modelMap.put(StarTigerWebConstant.ViewAttrKey.ERROR_MESSAGE, errorMessage);
            mv = new ModelAndView(StarTigerWebConstant.ViewName.ERROR_500, modelMap);
        }

        if (postWebErrorHandler != null) {
            postWebErrorHandler.exceptionHandler(request, response, handler, ex, mv);
        }

        return mv;
    }
}
