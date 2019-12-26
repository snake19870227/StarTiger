package com.snake19870227.stiger.admin.api.security;

import cn.hutool.core.map.MapUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.ContentType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.admin.api.entity.dto.AbstractRestResponse;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

/**
 * @author Bu HuaYang
 */
public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationSuccessHandler.class);

    @Value("${spring.application.name}")
    private String appName;

    @Value("${stiger.security.jwt.expiration-time}")
    private Duration duration = Duration.ofMinutes(30);

    @Autowired
    private JwtSignKey jwtSignKey;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        if (response.isCommitted()) {
            logger.warn("请求响应已被提交");
            return;
        }

        User user = (User) authentication.getPrincipal();

        Instant iat = Instant.now();
        Instant exp = iat.plus(duration);

        String token = Jwts.builder()
                .setIssuer(appName)
                .setIssuedAt(Date.from(iat))
                .setSubject(user.getUsername())
                .setAudience(user.getUsername())
                .setExpiration(Date.from(exp))
                .setId(user.getUsername())
                .signWith(jwtSignKey.getSignKey())
                .compact();

        logger.info("用户[{}]获取token[{}]", user.getUsername(), token);

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

        AbstractRestResponse.DefaultRestResponse restResponse = AbstractRestResponse.createSuccessRestResp(resultData);

        ServletUtil.write(response, objectMapper.writeValueAsString(restResponse), ContentType.build(MediaType.APPLICATION_JSON_VALUE, StandardCharsets.UTF_8));
    }
}
