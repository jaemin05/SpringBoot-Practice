package com.example.oauth.security.token;

import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
public class AuthToken {
    @Getter
    private final String token;
    private final Key key;

    private static final String AUTHORITIES_KEY = "role";

    //refreshToken 전용, role: X
    AuthToken(String id, Date expiry, Key key){
        this.key = key;
        this.token = createAuthToken(id, expiry);
    }

    //accessToken 전용
    AuthToken(String id, String role, Date expiry, Key key){
        this.key = key;
        this.token = createAuthToken(id, role, expiry);
    }

    private String createAuthToken(String id, Date expiry) {
        return Jwts.builder()
                .setSubject(id)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(expiry)
                .compact();
    }

    private String createAuthToken(String id, String role, Date expiry){
        return Jwts.builder()
                .setSubject(id)
                .claim(AUTHORITIES_KEY, role)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(expiry)
                .compact();
    }

    public boolean validate(){
        return this.getTokenClaims() != null;
    }
    public Claims getTokenClaims(){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch (SecurityException e){
            log.info("유효하지 않은 JWT 서명");
        }catch (MalformedJwtException e){
            log.info("유효하지 않은 JWT 토큰");
        }catch (ExpiredJwtException e){
            log.info("만료된 JWT 토큰");
        }catch (UnsupportedJwtException e){
            log.info("지원하지 않는 JWT 토큰");
        }catch (IllegalArgumentException e){
            log.info("비어있는 JWT");
        }
        return null;
    }

    public Claims getExpiryTokenClaims(){
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch (ExpiredJwtException e){
            log.info("만료된 JWT 토큰");
            return e.getClaims();
        }
    }
}
