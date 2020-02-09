package com.snake19870227.stiger.mall.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author Bu HuaYang
 */
@ConfigurationProperties(prefix = "stiger.mall.purchase")
@RefreshScope
public class StarTigerMallPurchaseProperties {

    private Integer limit;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
