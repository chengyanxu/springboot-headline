package com.xuxulearn.springbootheadline.utils;

import com.alibaba.druid.util.StringUtils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Data
@Component
public  class JwtHelper {

    @Value("${jwt.expiration}")
    private long tokenExpiration; // 有效时间,单位分钟
    @Value("${jwt.sign-key}")
    private String tokenSignKey;  // 当前程序签名秘钥

    // 生成签名密钥
    private SecretKey getSigningKey() {
        // 密钥长度检查
        if (tokenSignKey == null || tokenSignKey.length() < 32) {
            throw new IllegalArgumentException("JWT密钥长度不足，需要至少32字符");
        }
        return Keys.hmacShaKeyFor(tokenSignKey.getBytes(StandardCharsets.UTF_8));
    }

    // 生成token字符串
    public String createToken(Integer userId) {
        System.out.println("tokenExpiration = " + tokenExpiration + " 分钟");
        System.out.println("tokenSignKey = " + tokenSignKey);

        // 分钟转毫秒：tokenExpiration * 60 * 1000
        long expirationMillis = tokenExpiration * 60 * 1000L;

        String token = Jwts.builder()
                .subject("YYGH-USER")
                .expiration(new Date(System.currentTimeMillis() + expirationMillis))
                .claim("userId", userId)
                .signWith(getSigningKey(), Jwts.SIG.HS256) // 改为HS256，兼容32字符密钥
                .compact();
        return token;
    }

    // 其他方法保持不变...
    public  Long getUserId(String token) {
        if (StringUtils.isEmpty(token)) return null;
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            Claims claims = claimsJws.getPayload();
            Integer userId = (Integer) claims.get("userId");
            return userId.longValue();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isExpiration(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getExpiration()
                    .before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    public boolean validateToken(String token) {
        return !isExpiration(token);
    }
}