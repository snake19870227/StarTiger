package com.snake19870227.stiger.mall.security;

import cn.hutool.core.map.MapUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.ContentType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.core.StarTigerContext;
import com.snake19870227.stiger.core.restful.RestResponse;
import com.snake19870227.stiger.core.restful.RestResponseBuilder;
import com.snake19870227.stiger.mall.config.StarTigerMallSecurityProperties;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

/**
 * @author Bu HuaYang
 */
public class RestAuthenticationHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationHandler.class);

    private final StarTigerMallSecurityProperties securityProperties;

    private final JwtSignKey jwtSignKey;

    private final ObjectMapper objectMapper;

    public RestAuthenticationHandler(StarTigerMallSecurityProperties securityProperties, JwtSignKey jwtSignKey, ObjectMapper objectMapper) {
        this.securityProperties = securityProperties;
        this.jwtSignKey = jwtSignKey;
        this.objectMapper = objectMapper;
        logger.debug("创建 {}", this.getClass().getName());
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        if (response.isCommitted()) {
            logger.warn("请求响应已被提交");
            return;
        }

        AccountDetail accountDetail = (AccountDetail) authentication.getPrincipal();

        Instant iat = Instant.now();
        Instant exp = iat.plus(securityProperties.getExpirationTime());

        String token = Jwts.builder()
                .setIssuer(StarTigerContext.getApplicationName())
                .setIssuedAt(Date.from(iat))
                .setSubject(accountDetail.getUsername())
                .setAudience(accountDetail.getUsername())
                .setExpiration(Date.from(exp))
                .setId(accountDetail.getAccount().getAccountId())
                .signWith(jwtSignKey.getSignKey())
                .compact();

        logger.info("用户[{}]获取token[{}]", accountDetail.getAccount().getAccountId(), token);

        Map<Object, Object> resultData;
        resultData = MapUtil.of(new String[][] {
                {
                        "accessToken", token
                },
                {
                        "expiresTime",
                        LocalDateTime.ofInstant(exp, ZoneId.systemDefault())
                                .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"))
                }
        });

        RestResponse.DefaultRestResponse restResponse = RestResponseBuilder.createSuccessDefaultRestResp(resultData);

        ServletUtil.write(response, objectMapper.writeValueAsString(restResponse), ContentType.build(MediaType.APPLICATION_JSON_VALUE, StandardCharsets.UTF_8));
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (response.isCommitted()) {
            logger.warn("请求响应已被提交");
            return;
        }

        RestResponse.DefaultRestResponse restResponse = RestResponseBuilder.createDefaultRestResp(false, "1001", null);

        response.setStatus(HttpStatus.OK.value());

        ServletUtil.write(response, objectMapper.writeValueAsString(restResponse), ContentType.build(MediaType.APPLICATION_JSON_VALUE, StandardCharsets.UTF_8));
    }
}
