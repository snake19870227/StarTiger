package com.snake19870227.stiger.mall.remote;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.snake19870227.stiger.mall.config.StarTigerMallServiceProperties;
import com.snake19870227.stiger.mall.entity.dto.AccountDetailRestResponse;
import com.snake19870227.stiger.mall.entity.dto.BetchGetGoodsListRestResponse;
import com.snake19870227.stiger.mall.entity.po.MallAccount;
import com.snake19870227.stiger.mall.entity.po.MallGoods;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Bu HuaYang
 */
public class MallCloudRpcService extends BaseMallCloudRpcService {

    private static final Logger logger = LoggerFactory.getLogger(MallCloudRpcService.class);

    public MallCloudRpcService(StarTigerMallServiceProperties serviceProperties, RestTemplate cloudRestTemplate) {
        super(serviceProperties, cloudRestTemplate);
        logger.debug("创建 {}", this.getClass().getName());
    }

    public MallAccount getAccountInfo(Claims claims, String jwtToken) {

        RequestEntity<Object> requestEntity
                = createGetRequestEntity("account", jwtToken, "/account/detail?accountId=" + claims.getId());

        AccountDetailRestResponse restResponse = execute(requestEntity, AccountDetailRestResponse.class);

        return restResponse.getData();
    }

    public List<MallGoods> getGoodsList(List<String> goodsIdList, String jwtToken) {

        RequestEntity<Object> requestEntity
                = createPostRequestEntity("goods", jwtToken, "/goods/select", goodsIdList);

        BetchGetGoodsListRestResponse restResponse = execute(requestEntity, BetchGetGoodsListRestResponse.class);

        if (CollectionUtil.isNotEmpty(restResponse.getNotFoundIdList())) {
            StringBuilder ids = new StringBuilder("[");
            ids.append(CollUtil.join(restResponse.getNotFoundIdList(), ",")).append("]");
            logger.warn("未找到下列商品: {}", ids);
        }

        return restResponse.getData();
    }
}
