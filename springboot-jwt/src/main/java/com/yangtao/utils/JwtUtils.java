package com.yangtao.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JwtUtils {
    private static final String SING = "!r3df(";
    /**
     * 生成Token
     */
    public static String getToken(Map<String, String> map) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 3);
        JWTCreator.Builder jwtBuilder = JWT.create();

        map.forEach((k, v) -> {
            jwtBuilder.withClaim(k, v);
        });

        // 制定过期时间和签名算法
        String token = jwtBuilder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256("!r3df("));

        return token;
    }

    /**
     * 验证Token
     */
    public static void verify(String token) {
        JWT.require(Algorithm.HMAC256("!r3df(")).build().verify(token);
    }

    /**
     * 获取payload中的信息
     */
    public static DecodedJWT getTokenInfo(String token) {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256("!r3df(")).build().verify(token);
        return jwt;
    }

}
