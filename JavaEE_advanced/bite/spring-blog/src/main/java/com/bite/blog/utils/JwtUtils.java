package com.bite.blog.utils;

import com.bite.blog.constants.Constant;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtUtils {
    //过期时间: 1小时的毫秒数
    private final static long EXPIRATION_DATE = 60 * 60 * 1000;
    private final static String secretString = "ND/G/LsRFDTC88R/ua9ZqGn3ueqHC5Els255MdPMiF4=";
    private final static Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));

    //生成令牌
    public static String genToken(Map<String, Object> claim){
       return Jwts.builder()
                .setClaims(claim)
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_DATE))
                .signWith(key)
                .compact();
    }
    /**
     * 解析令牌
     * @param token
     * @return
     */
    public static Claims parseToken(String token){
        JwtParser build = Jwts.parserBuilder().setSigningKey(key).build();
        Claims body = null;
        try {
            body = build.parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            log.error("token过期, 校验失败, token:",token);

        } catch (Exception e) {
            log.error("token校验失败, token:",token);
        }
        return body;
    }
    //校验令牌
    public static boolean checkToken(String token){
        Claims body = parseToken(token);
        if (body==null){
            return false;
        }
        return true;
    }
    public static Integer getUserIdFromToken(String token){
        Claims body = parseToken(token);
        if (body!=null){
            return (Integer) body.get(Constant.USER_CLAIM_ID);
        }
        return null;
    }
}
