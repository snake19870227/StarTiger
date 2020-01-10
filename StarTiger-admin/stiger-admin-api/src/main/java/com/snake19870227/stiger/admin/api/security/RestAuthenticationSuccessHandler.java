package com.snake19870227.stiger.admin.api.security;

import cn.hutool.core.map.MapUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake19870227.stiger.admin.entity.dto.RestResponse;
import com.snake19870227.stiger.admin.security.JwtSignKey;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
public class RestAuthenticationSuccessHandler extends BaseRestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

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

        doHandler(request, response, authentication);
    }

    @Override
    protected RestResponse.DefaultRestResponse buildResponse(HttpServletRequest request, HttpServletResponse response, Object object) {

        if (object instanceof Authentication) {

            Authentication authentication = (Authentication) object;

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

            return RestResponse.createSuccessRestResp(resultData);
        }

        return RestResponse.createRestResp(false, "1001", null);
    }
}
