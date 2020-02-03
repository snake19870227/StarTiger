package com.snake19870227.stiger.cloud.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Bu HuaYang
 */
public abstract class VisitorFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(VisitorFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String somebody = request.getParameter("somebody");
        String servletPath = request.getServletPath();
        if (StrUtil.isNotBlank(somebody)) {
            Integer times = getVisitTimes(somebody);
            logger.info("{} 第 {} 次访问 {}", somebody, times, servletPath);
        }
        return null;
    }

    protected abstract Integer getVisitTimes(String visitor);
}
