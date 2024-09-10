package org.suhodo.carserver.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;

@Component
public class JwtService {

    /* 토큰 발급시 필요 정보*/
    // EXPIRATION_TIME시간을 밀리세컨드(ms)단위로 부여해야 함
    static final long EXPIRATION_TIME = 60 * 60 * 24 * 1 * 1000; // 1일=86,400,000, 토큰 유효시간, 60초, 60분, 24시간, 1일, 1000ms
    static final String PREFIX = "Bearer";                // jwt 토큰 앞에 붙이는 문자열
    /*토큰을 발행/검증 시 사용하는 비밀키*/
    static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);0
    
    // 토큰 발급 메서드(비밀키로 서명이 이루어짐)
    public String getToken(String username){
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();

        return token;
    }
    
    // 클라이언트가 보내온 토큰에서 username을 추출하는 메서드
    public String getAuthUser(HttpServletRequest request){
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        // 토큰이 헤더에 존재한다면
        if(token != null){
            String user = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token.replace(PREFIX, ""))
                    .getBody()
                    .getSubject();

            if(user != null)
                return user;
        }

        return null;
    }
}











