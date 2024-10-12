package com.baimi.init.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.util.Map;

/**
 * @author zhang
 * DATE 2024/6/25 下午3:08
 */

@Slf4j
public class JwtUtil {

    /**
     * 生成jwt
     * 使用Hs256算法，私钥使用固定密钥
     */
    private static final String secretKey = "ifjefijohweueurjogbkrnaumavalvmoi";
    public static String createJWT(Map<String, Object> claims){
        //指定加密算法 HS256
        SecureDigestAlgorithm<SecretKey, SecretKey> algorithm = Jwts.SIG.HS256;
        //密钥实例
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        return Jwts.builder()
                .signWith(key, algorithm) //设置签名使用的签名算法和签名使用的秘钥
                .claims(claims) //设置自定义负载信息
                .compact();
    }

    /**
     * 解析jwt
     */
    public static Jws<Claims> parseJWT(String token){
        //密钥实例
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.parser()
                .verifyWith(key)  //设置签名的密钥
                .build()
                .parseSignedClaims(token);
    }

}
