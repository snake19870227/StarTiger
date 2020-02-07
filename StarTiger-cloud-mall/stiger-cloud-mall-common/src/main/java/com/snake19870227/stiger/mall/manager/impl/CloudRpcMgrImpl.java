package com.snake19870227.stiger.mall.manager.impl;

import cn.hutool.core.util.StrUtil;
import com.snake19870227.stiger.StarTigerConstant;
import com.snake19870227.stiger.mall.common.StarTigerMallServiceProperties;
import com.snake19870227.stiger.mall.entity.dto.AccountDetailRestResponse;
import com.snake19870227.stiger.mall.entity.po.MallAccount;
import com.snake19870227.stiger.mall.exception.CloudRpcException;
import com.snake19870227.stiger.mall.manager.CloudRpcMgr;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Optional;

/**
 * @author Bu HuaYang
 */
@Component
public class CloudRpcMgrImpl implements CloudRpcMgr {

    private static final Logger logger = LoggerFactory.getLogger(CloudRpcMgrImpl.class);

    private final StarTigerMallServiceProperties serviceProperties;

    private final RestTemplate cloudRestTemplate;

    public CloudRpcMgrImpl(StarTigerMallServiceProperties serviceProperties, RestTemplate cloudRestTemplate) {
        this.serviceProperties = serviceProperties;
        this.cloudRestTemplate = cloudRestTemplate;
        logger.debug("创建 {}", this.getClass().getName());
    }

    @Override
    public MallAccount getAccountInfo(Claims claims, String jwtToken) {

        String serviceUrl = Optional.ofNullable(serviceProperties.getRouter().get("account"))
                .map(StarTigerMallServiceProperties.ServiceInfo::getUrl).orElse(null);

        if (StrUtil.isBlank(serviceUrl)) {
            throw new CloudRpcException("6000");
        }

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add(HttpHeaders.AUTHORIZATION, StarTigerConstant.OAuth20Code.BEARER_TOKEN_PREFIX + jwtToken);

        RequestEntity<Object> requestEntity
                = new RequestEntity<>(requestHeaders, HttpMethod.GET, URI.create(serviceUrl + "/account/detail?accountId=" + claims.getId()));

        ResponseEntity<AccountDetailRestResponse> responseEntity
                = cloudRestTemplate.exchange(requestEntity, AccountDetailRestResponse.class);

        Optional<AccountDetailRestResponse> restResponse = Optional.ofNullable(responseEntity.getBody());

        if (restResponse.isPresent()) {
            logger.info("账户服务返回: {}", restResponse.get());
            return restResponse.filter(accountDetailRestResponse -> StrUtil.equals(StarTigerConstant.StatusCode.CODE_0000, accountDetailRestResponse.getRespCode()))
                    .flatMap(accountDetailRestResponse -> Optional.ofNullable(accountDetailRestResponse.getData()))
                    .orElseThrow(() -> new CloudRpcException("5001"));
        }

        throw new CloudRpcException("6001");
    }
}
