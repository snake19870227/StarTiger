package com.snake19870227.stiger.cloud.base.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * @author Bu HuaYang
 */
@ConfigurationProperties(prefix = "stiger.cloud")
public class StarTigerCloudProperties {

    private Map<String, String> producers;

    private List<String> nextServices;

    public Map<String, String> getProducers() {
        return producers;
    }

    public void setProducers(Map<String, String> producers) {
        this.producers = producers;
    }

    public List<String> getNextServices() {
        return nextServices;
    }

    public void setNextServices(List<String> nextServices) {
        this.nextServices = nextServices;
    }
}
