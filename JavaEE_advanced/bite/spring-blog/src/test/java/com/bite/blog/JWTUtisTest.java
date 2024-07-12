package com.bite.blog;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtisTest {
    //过期时间: 1小时的毫秒数
    private final static long EXPIRATION_DATE = 60 * 60 * 1000;
    private final static String secretString = "ND/G/LsRFDTC88R/ua9ZqGn3ueqHC5Els255MdPMiF4=";
    private final static Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));

    //生成令牌
    @Test
    public void genToken(){

        Map<String, Object> claim = new HashMap<>();
        claim.put("id", 8);
        claim.put("name", "lisi");

        String token = Jwts.builder()
                .setClaims(claim)
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_DATE))
                .signWith(key)
                .compact();
        System.out.println(token);
    }

    //生成key
    @Test
    public void genKey(){
        //生成key
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String encode = Encoders.BASE64.encode(secretKey.getEncoded());
        System.out.println(encode);
    }
    //校验令牌
    @Test
    public void parseToken(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoibGlzaSIsImlkIjo4LCJleHAiOjE3MDkzNDg4NDR9.NEa7UKPI8rPNKVm_DKSqVp6GHDIsWWK88ZplPU6-CLM";
        JwtParser build = Jwts.parserBuilder().setSigningKey(key).build();
        Claims body = null;
        try {
            body = build.parseClaimsJws(token).getBody();
        } catch (Exception e) {
            System.out.println("令牌校验失败");
        }
        System.out.println(body);
    }
}
