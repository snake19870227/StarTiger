package com.snake19870227.stiger.admin.security;

import com.snake19870227.stiger.admin.service.JwtKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;

/**
 * @author Bu HuaYang
 */
public class JwtRsaSignKey implements JwtSignKey {

    @Value("${stiger.security.jwt.public-key-code:jwt-public-key}")
    private String publicKeyCode;
    @Value("${stiger.security.jwt.private-key-code:jwt-private-key}")
    private String privateKeyCode;

    @Autowired
    private JwtKeyService jwtKeyService;

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

    private synchronized void loadKey() {

        Map<String, Key> keyMap = jwtKeyService.loadJwtKey();

        this.keyPair = new KeyPair(
                (PublicKey) keyMap.get(publicKeyCode),
                (PrivateKey) keyMap.get(privateKeyCode)
        );
    }
}
