package com.snake19870227.stiger.mall.manager.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.snake19870227.stiger.StarTigerConstant;
import com.snake19870227.stiger.mall.common.StarTigerMallServiceProperties;
import com.snake19870227.stiger.mall.entity.dto.AccountDetailRestResponse;
import com.snake19870227.stiger.mall.entity.dto.BetchGetGoodsListRestResponse;
import com.snake19870227.stiger.mall.entity.po.MallAccount;
import com.snake19870227.stiger.mall.entity.po.MallGoods;
import com.snake19870227.stiger.mall.exception.CloudRpcException;
import com.snake19870227.stiger.mall.manager.BaseCloudRpcManager;
import com.snake19870227.stiger.mall.manager.CloudRpcMgr;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author Bu HuaYang
 */
@Component
public class CloudRpcMgrImpl extends BaseCloudRpcManager implements CloudRpcMgr {

    private static final Logger logger = LoggerFactory.getLogger(CloudRpcMgrImpl.class);

    public CloudRpcMgrImpl(StarTigerMallServiceProperties serviceProperties, RestTemplate cloudRestTemplate) {
        super(serviceProperties, cloudRestTemplate);
        logger.debug("创建 {}", this.getClass().getName());
    }

    @Override
    public MallAccount getAccountInfo(Claims claims, String jwtToken) {

        RequestEntity<Object> requestEntity
                = createGetRequestEntity("account", jwtToken, "/account/detail?accountId=" + claims.getId());

        AccountDetailRestResponse restResponse = execute(requestEntity, AccountDetailRestResponse.class);

        return restResponse.getData();
    }

    @Override
    public List<MallGoods> getGoodsList(List<String> goodsIdList, String jwtToken) {

        RequestEntity<Object> requestEntity
                = createPostRequestEntity("account", jwtToken, "/goods/select", goodsIdList);

        BetchGetGoodsListRestResponse restResponse = execute(requestEntity, BetchGetGoodsListRestResponse.class);

        if (CollectionUtil.isNotEmpty(restResponse.getNotFoundIdList())) {
            StringBuilder ids = new StringBuilder("[");
            ids.append(CollUtil.join(restResponse.getNotFoundIdList(), ",")).append("]");
            logger.warn("未找到下列商品: {}", ids);
        }

        return restResponse.getData();
    }
}
