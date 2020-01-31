package com.snake19870227.stiger.cloud.consumer.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author Bu HuaYang
 */
@ConfigurationProperties(prefix = "stiger.cloud")
public class StarTigerCloudProperties {

    private Map<String, String> producers;

    public Map<String, String> getProducers() {
        return producers;
    }

    public void setProducers(Map<String, String> producers) {
        this.producers = producers;
    }
}
