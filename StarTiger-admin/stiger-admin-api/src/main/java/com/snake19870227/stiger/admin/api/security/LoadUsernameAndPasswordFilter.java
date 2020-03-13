package com.snake19870227.stiger.admin.api.security;

import cn.hutool.extra.servlet.ServletUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.web.servlet.ParameterAttributeHttpServletRequestWrapper;
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

import static com.snake19870227.stiger.admin.api.config.SecurityConfig.LOGIN_PROCESSING_URL;
import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY;
import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;

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

            String requestBody = ServletUtil.getBody(requestWrapper);

            JsonNode jsonNode = objectMapper.readTree(requestBody);

            JsonNode usernameNode = jsonNode.get(SPRING_SECURITY_FORM_USERNAME_KEY);
            JsonNode passwordNode = jsonNode.get(SPRING_SECURITY_FORM_PASSWORD_KEY);

            if (usernameNode != null) {

                requestWrapper.setAttribute(SPRING_SECURITY_FORM_USERNAME_KEY, usernameNode.textValue());
                requestWrapper.setAttribute(SPRING_SECURITY_FORM_PASSWORD_KEY, passwordNode.textValue());
            }
        }

        chain.doFilter(requestWrapper, response);
    }
}
