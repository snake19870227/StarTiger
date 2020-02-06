package com.snake19870227.stiger.mall.common;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * @author Bu HuaYang
 */
@ConfigurationProperties(prefix = "stiger.mall.security")
public class StarTigerMallSecurityProperties {

    private String loginProcessingUrl;

    private String publicKey;
    private String privateKey;
    private Duration expirationTime = Duration.ofMinutes(30);

    public String getLoginProcessingUrl() {
        return loginProcessingUrl;
    }

    public void setLoginProcessingUrl(String loginProcessingUrl) {
        this.loginProcessingUrl = loginProcessingUrl;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public Duration getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Duration expirationTime) {
        this.expirationTime = expirationTime;
    }
}
