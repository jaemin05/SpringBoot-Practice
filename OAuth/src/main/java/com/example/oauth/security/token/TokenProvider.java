package com.example.oauth.security.token;

import com.example.oauth.config.JwtProperties;
import com.example.oauth.domain.refreshToken.UserRefreshToken;
import com.example.oauth.domain.refreshToken.UserRefreshTokenRepository;
import com.example.oauth.dto.response.TokenResponse;
import com.example.oauth.exception.ExpiredTokenException;
import com.example.oauth.exception.InvalidTokenException;
import com.example.oauth.exception.TokenValidFailedException;
import com.nimbusds.oauth2.sdk.token.RefreshToken;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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

    public TokenResponse generateToken(String id, String role){
        String access = generateAccessToken(id , role);
        String refresh = generateRefreshToken(id, role);

        refreshTokenRepository.save(
                UserRefreshToken.builder()
                        .userId(id)
                        .refreshToken(refresh)
                        .refreshExpiration(jwtProperties.getRefreshTokenExp())
                        .build()
        );

        return new TokenResponse(access, refresh);
    }

    public String generateAccessToken(String id, String role){
        return Jwts.builder()
                .setSubject(id)
                .claim("type", "access")
                .claim("role", role)
                .signWith(SignatureAlgorithm.ES256, jwtProperties.getSecretKey())
                .setExpiration(
                        new Date(System.currentTimeMillis() + jwtProperties.getAccessTokenExp() * 1000)
                )
                .setIssuedAt(new Date())
                .compact();
    }

    public String generateRefreshToken(String id, String role){
        return Jwts.builder()
                .setSubject(id)
                .claim("type", "refresh")
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .setExpiration(
                        new Date(System.currentTimeMillis() + jwtProperties.getRefreshTokenExp() * 1000)
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

    private Claims getBody(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.Exception;
        } catch (JwtException e) {
            throw InvalidTokenException.Exception;
        }
    }

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }


}
