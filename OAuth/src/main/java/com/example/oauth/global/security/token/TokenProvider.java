package com.example.oauth.global.security.token;

import com.example.oauth.global.config.JwtProperties;
import com.example.oauth.domain.refreshToken.UserRefreshTokenRepository;
import com.example.oauth.global.exception.ExpiredTokenException;
import com.example.oauth.global.exception.InvalidTokenException;
import com.example.oauth.global.exception.TokenValidFailedException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TokenProvider {
    private final JwtProperties jwtProperties;
    private final UserRefreshTokenRepository refreshTokenRepository;

    public String generateAccessToken(String id, String role){
        return Jwts.builder()
                .setSubject(id)
                .claim("type", "access")
                .claim("role", role)
                .signWith(SignatureAlgorithm.ES256, jwtProperties.getAuth().getSecretToken())
                .setExpiration(
                        new Date(System.currentTimeMillis() + jwtProperties.getAuth().getAccessTokenExp() * 1000)
                )
                .setIssuedAt(new Date())
                .compact();
    }

    public String generateRefreshToken(String id, String role){
        return Jwts.builder()
                .setSubject(id)
                .claim("type", "refresh")
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getAuth().getSecretToken())
                .setExpiration(
                        new Date(System.currentTimeMillis() + jwtProperties.getAuth().getRefreshTokenExp() * 1000)
                )
                .setIssuedAt(new Date())
                .compact();
    }

    public Authentication getAuthentication(String token) {
        if(validate(token)) {
            Claims claims = getBody(token);
            Collection<? extends GrantedAuthority> authorities =
                    Arrays.stream(new String[]{claims.get("role").toString()})
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

            User principal = new User(claims.getSubject(), "", authorities);

            return new UsernamePasswordAuthenticationToken(principal, "", authorities);
        } else{
            throw TokenValidFailedException.Exception;
        }
    }

    public boolean validate(String token) {
        return this.getBody(token) != null;
    }

    public boolean isRefreshToken(String refreshToken){
        return getBody(refreshToken).get("type").equals("refresh");
    }

    private Claims getBody(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(jwtProperties.getAuth().getSecretToken())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.Exception;
        } catch (JwtException e) {
            throw InvalidTokenException.Exception;
        }
    }

    public boolean checkRole(String token, String role){
        return getBody(token).get("role").equals(role);
    }

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }


}
