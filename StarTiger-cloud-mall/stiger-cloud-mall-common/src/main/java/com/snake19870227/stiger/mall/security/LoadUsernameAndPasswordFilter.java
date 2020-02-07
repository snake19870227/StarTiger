package com.snake19870227.stiger.mall.security;

import cn.hutool.extra.servlet.ServletUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.mall.common.StarTigerMallSecurityProperties;
import com.snake19870227.stiger.servlet.ParameterAttributeHttpServletRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Bu HuaYang
 */
public class LoadUsernameAndPasswordFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(LoadUsernameAndPasswordFilter.class);

    private final RequestMatcher targetUrlMatcher;

    private final ObjectMapper objectMapper;

    private final StarTigerMallSecurityProperties securityProperties;

    public LoadUsernameAndPasswordFilter(ObjectMapper objectMapper, StarTigerMallSecurityProperties securityProperties) {

        this.objectMapper = objectMapper;
        this.securityProperties = securityProperties;

        this.targetUrlMatcher = new AntPathRequestMatcher(securityProperties.getLoginProcessingUrl(), HttpMethod.POST.name());

        logger.debug("创建 {}", this.getClass().getName());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        ParameterAttributeHttpServletRequestWrapper requestWrapper = new ParameterAttributeHttpServletRequestWrapper((HttpServletRequest) request);

        boolean isConform = this.targetUrlMatcher.matches(requestWrapper);

        if (isConform) {

            String requestBody = ServletUtil.getBody(requestWrapper);

            JsonNode jsonNode = objectMapper.readTree(requestBody);

            JsonNode usernameNode = jsonNode.get(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY);
            JsonNode passwordNode = jsonNode.get(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY);

            if (usernameNode != null) {

                requestWrapper.setAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, usernameNode.textValue());
                requestWrapper.setAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY, passwordNode.textValue());
            }
        }

        chain.doFilter(requestWrapper, response);
    }
}
