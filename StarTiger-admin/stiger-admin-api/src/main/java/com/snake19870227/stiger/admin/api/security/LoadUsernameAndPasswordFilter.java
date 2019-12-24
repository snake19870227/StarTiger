package com.snake19870227.stiger.admin.api.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.admin.servlet.ParameterAttributeHttpServletRequestWrapper;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.snake19870227.stiger.admin.api.config.SecurityConfig.*;

/**
 * @author Bu HuaYang
 */
public class LoadUsernameAndPasswordFilter extends GenericFilterBean {

    private RequestMatcher targetUrlMatcher;

    private ObjectMapper objectMapper;

    public LoadUsernameAndPasswordFilter(ObjectMapper objectMapper) {

        this.objectMapper = objectMapper;

        this.targetUrlMatcher = new AntPathRequestMatcher(LOGIN_PROCESSING_URL, HttpMethod.POST.name());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        ParameterAttributeHttpServletRequestWrapper requestWrapper = new ParameterAttributeHttpServletRequestWrapper((HttpServletRequest) request);

        boolean isConform = this.targetUrlMatcher.matches(requestWrapper);

        if (isConform) {


        }

        chain.doFilter(request, response);
    }
}
