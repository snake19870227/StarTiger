package com.snake19870227.stiger.mall.remote;

import cn.hutool.core.util.StrUtil;
import com.snake19870227.stiger.core.StarTigerConstant;
import com.snake19870227.stiger.http.RestResponse;
import com.snake19870227.stiger.mall.config.StarTigerMallServiceProperties;
import com.snake19870227.stiger.mall.exception.CloudRpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.Optional;

/**
 * @author Bu HuaYang
 */
public abstract class BaseMallCloudRpcService {

    private static final Logger logger = LoggerFactory.getLogger(BaseMallCloudRpcService.class);

    private final StarTigerMallServiceProperties serviceProperties;

    private final RestTemplate cloudRestTemplate;

    protected BaseMallCloudRpcService(StarTigerMallServiceProperties serviceProperties, RestTemplate cloudRestTemplate) {
        this.serviceProperties = serviceProperties;
        this.cloudRestTemplate = cloudRestTemplate;
    }

    protected HttpHeaders createRpcHeaders(String jwtToken) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        requestHeaders.add(HttpHeaders.AUTHORIZATION, StarTigerConstant.OAuth20Code.BEARER_TOKEN_PREFIX + jwtToken);
        return requestHeaders;
    }

    protected RequestEntity<Object> createGetRequestEntity(String serviceName, String jwtToken, String requestUrl) {
        return createRequestEntity(serviceName, jwtToken, requestUrl, HttpMethod.GET, null);
    }

    protected RequestEntity<Object> createPostRequestEntity(String serviceName, String jwtToken, String requestUrl, Object requestBody) {
        return createRequestEntity(serviceName, jwtToken, requestUrl, HttpMethod.POST, requestBody);
    }

    protected RequestEntity<Object> createRequestEntity(String serviceName, String jwtToken, String requestUrl, HttpMethod httpMethod, Object requestBody) {

        String serviceUrl = Optional.ofNullable(serviceProperties.getRouter().get(serviceName))
                .map(StarTigerMallServiceProperties.ServiceInfo::getUrl).orElse(null);

        if (StrUtil.isBlank(serviceUrl)) {
            throw new CloudRpcException("6000");
        }

        HttpHeaders requestHeaders = createRpcHeaders(jwtToken);
        return new RequestEntity<>(requestBody, requestHeaders, httpMethod, URI.create(serviceUrl + requestUrl));
    }

    protected <T extends RestResponse<?>> T execute(RequestEntity<?> requestEntity, Class<T> responseClass) {

        ResponseEntity<T> responseEntity = cloudRestTemplate.exchange(requestEntity, responseClass);

        Optional<T> restResponse = Optional.ofNullable(responseEntity.getBody());

        T response = restResponse.orElseThrow(() -> new CloudRpcException("6001"));

        logger.info("\n服务 [{}] 返回\n{}", requestEntity.getUrl(), response);

        if (!StrUtil.equals(StarTigerConstant.StatusCode.CODE_0000, response.getRespCode())) {
            throw new CloudRpcException("6999", response.getRespMessage());
        }

        return response;
    }
}
