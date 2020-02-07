package com.snake19870227.stiger.mall.security;

import cn.hutool.core.util.StrUtil;
import com.snake19870227.stiger.StarTigerConstant;
import com.snake19870227.stiger.http.RestResponse;
import com.snake19870227.stiger.mall.entity.bo.AccountDetail;
import com.snake19870227.stiger.mall.entity.dto.AccountDetailRestResponse;
import com.snake19870227.stiger.mall.entity.po.MallAccount;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Optional;

/**
 * @author Bu HuaYang
 */
public class CloudJwtAuthenticationFilter extends BaseJwtAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(CloudJwtAuthenticationFilter.class);

    private final RestTemplate cloudRestTemplate;

    public CloudJwtAuthenticationFilter(JwtSignKey jwtSignKey, RestTemplate cloudRestTemplate) {
        super(jwtSignKey);
        this.cloudRestTemplate = cloudRestTemplate;
        logger.debug("创建 {}", this.getClass().getName());
    }

    @Override
    protected UserDetails loadUserDetails(Claims claims, String bearerJwtToken) {

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add(HttpHeaders.AUTHORIZATION, bearerJwtToken);

        RequestEntity<Object> requestEntity
                = new RequestEntity<>(null, requestHeaders, HttpMethod.GET, URI.create("http://mall-account/account/detail?accountId=" + claims.getId()));

        ResponseEntity<AccountDetailRestResponse> responseEntity
                = cloudRestTemplate.exchange(
                        "http://mall-account/account/detail?accountId=" + claims.getId(),
                        HttpMethod.GET,
                        requestEntity,
                        AccountDetailRestResponse.class
        );

        Optional<AccountDetailRestResponse> restResponse = Optional.ofNullable(responseEntity.getBody());

        MallAccount account = null;

        if (restResponse.isPresent()) {
            account = restResponse.filter(accountDetailRestResponse -> {
                logger.info("账户服务返回 [{}]", accountDetailRestResponse.getRespMessage());
                return StrUtil.equals(StarTigerConstant.StatusCode.CODE_0000, accountDetailRestResponse.getRespCode());
            }).map(RestResponse::getData).orElse(null);
        }

        if (account == null) {
            logger.warn("未获取到账户信息");
            return null;
        }

        return new AccountDetail(account);
    }
}
