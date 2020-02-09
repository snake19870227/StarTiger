package com.snake19870227.stiger.mall.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bu HuaYang
 */
@ConfigurationProperties(prefix = "stiger.mall.service")
public class StarTigerMallServiceProperties {

    private Map<String, ServiceInfo> router;

    public Map<String, ServiceInfo> getRouter() {
        if (router == null) {
            router = new HashMap<>();
        }
        return router;
    }

    public void setRouter(Map<String, ServiceInfo> router) {
        this.router = router;
    }

    public static class ServiceInfo {

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
