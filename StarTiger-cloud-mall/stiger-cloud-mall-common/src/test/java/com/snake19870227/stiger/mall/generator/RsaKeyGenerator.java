package com.snake19870227.stiger.mall.generator;

import cn.hutool.core.codec.Base64;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.KeyPair;

/**
 * @author Bu HuaYang
 */
public class RsaKeyGenerator {

    public static void main(String[] args) {
        KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
        System.out.println(Base64.encode(keyPair.getPublic().getEncoded()));
        System.out.println(Base64.encode(keyPair.getPrivate().getEncoded()));
    }
}
