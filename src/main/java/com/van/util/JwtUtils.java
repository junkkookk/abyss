package com.van.util;



import com.van.common.exception.CustomException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Optional;

/**
 * @author junkkook
 */
@Slf4j
public class JwtUtils {
    public static final long EXPIRE = 1000 * 60 * 60 * 24;
    public static final long LONG_EXPIRE = 1000 * 60 * 60 * 24 * 7;
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";

    //根据用户名创建token
    public static String createTokenLongExpire(Long username){
        return "Bearer "+Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("地市信息")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + LONG_EXPIRE))
                .claim("username",username)
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
    }

    //根据用户名创建token
    public static String createToken(String username){
        return "Bearer "+Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("地市信息")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim("username",username)
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
    }

    public static Optional<String> getUserNameByToken(String jwtToken) {
        try {
            Jws<Claims> claimsJws =
                    Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
            return Optional.ofNullable((String) claimsJws.getBody().get("username"));
        }catch (ExpiredJwtException e){
            throw new CustomException("token已过期");
        }
    }


    public static Optional<String> getTokenFromHeader(String header){
        if (header==null){
            return Optional.empty();
        }else {
            String[] split = header.split(" ");
            if (split.length < 2){
                return Optional.empty();
            }else {
                return Optional.ofNullable(split[1]);
            }
        }
    }

}
