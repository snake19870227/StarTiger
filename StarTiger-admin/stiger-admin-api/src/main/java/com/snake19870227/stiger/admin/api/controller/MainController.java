package com.snake19870227.stiger.admin.api.controller;

import cn.hutool.crypto.asymmetric.RSA;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import static com.snake19870227.stiger.admin.api.config.SecurityConfig.*;

/**
 * @author Bu HuaYang
 */
@RestController
@RequestMapping(path = LOGIN_PRE_PATH)
public class MainController {

    @PostMapping(path = LOGIN_FAILURE_PATH)
    public Object loginFailure() {
        return HttpStatus.UNAUTHORIZED.value();
    }

    @PostMapping(path = LOGIN_SUCCESS_PATH)
    public Object loginSuccess() {
        return HttpStatus.OK.value();
    }

    public static void main(String[] args) throws InterruptedException {

        Instant instant = Instant.now();

        KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);

        RSA rsa = new RSA(keyPair.getPrivate(), keyPair.getPublic());



        System.out.println(rsa.getPrivateKeyBase64());
        System.out.println(rsa.getPublicKeyBase64());

        String token = Jwts.builder().setIssuer("snake")
                .setIssuedAt(Date.from(instant))
                .setSubject("xixi")
                .setAudience("xixi")
                .setExpiration(Date.from(instant.plus(Duration.parse("PT5S"))))
                .setId(UUID.randomUUID().toString())
                .signWith(keyPair.getPrivate())
                .compact();

        System.out.println(token);

        Thread.sleep(3000);

        Jws<Claims> jws = Jwts.parser().setSigningKey(keyPair.getPublic()).parseClaimsJws(token);

        System.out.println(jws.toString());

        Thread.sleep(3000);

        Jwts.parser().setSigningKey(keyPair.getPublic()).parseClaimsJws(token);
    }

}
