package com.snake19870227.stiger.admin.security;

import cn.hutool.crypto.asymmetric.RSA;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.security.KeyPair;

/**
 * @author Bu HuaYang
 */
public class JwtRsaSignKey extends AbstractJwtSignKeyStorage implements JwtSignKey {

    @Value("${stiger.security.jwt.public-key-code:jwt-public-key}")
    private String publicKeyCode;
    @Value("${stiger.security.jwt.private-key-code:jwt-private-key}")
    private String privateKeyCode;

    protected KeyPair keyPair;

    @Override
    public void init() {
        loadKey();
    }

    @Override
    public Key getSignKey() {
        if (keyPair == null) {
            loadKey();
        }
        return keyPair.getPrivate();
    }

    @Override
    public Key getSigningKey() {
        if (keyPair == null) {
            loadKey();
        }
        return keyPair.getPublic();
    }

    private void delRsaKey() {
        delKey(publicKeyCode);
        delKey(privateKeyCode);
    }

    private synchronized void loadKey() {

        String publicKey = getKey(publicKeyCode);
        String privateKey = getKey(privateKeyCode);

        if (StringUtils.isNotBlank(publicKey) && StringUtils.isNotBlank(privateKey)) {
            RSA rsa = new RSA(privateKey, publicKey);
            this.keyPair = new KeyPair(rsa.getPublicKey(), rsa.getPrivateKey());
        } else {
            delRsaKey();
            this.keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
            RSA rsa = new RSA(this.keyPair.getPrivate(), this.keyPair.getPublic());
            saveKey(publicKeyCode, rsa.getPublicKeyBase64());
            saveKey(privateKeyCode, rsa.getPrivateKeyBase64());
        }
    }
}
