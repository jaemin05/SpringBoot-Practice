package com.example.fcmpractice.global.security.jwt;

import com.example.fcmpractice.domain.auth.domain.RefreshToken;
import com.example.fcmpractice.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.fcmpractice.global.exception.ExpiredJwtException;
import com.example.fcmpractice.global.exception.InvalidJwtException;
import com.example.fcmpractice.global.security.auth.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final AuthDetailsService authDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    private String generateToken(String accountId, String type, Long exp) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .setSubject(accountId)
                .claim("typ", type)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .compact();
    }

    public String generateAccessToken(String accountId) {
        return generateToken(accountId, "access", jwtProperties.getAccessExp());
    }

    public String generateRefreshToken(String accountId) {
        String refresh = generateToken(accountId, "refresh", jwtProperties.getRefreshExp());

        refreshTokenRepository.save(
                RefreshToken.builder()
                        .id(accountId)
                        .refreshToken(refresh)
                        .ttl(jwtProperties.getRefreshExp())
                        .build());
        return refresh;
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(jwtProperties.getHeader());
        if (bearerToken != null && bearerToken.startsWith(jwtProperties.getPrefix()) && bearerToken.length() > 7)
            return bearerToken.substring(7);
        return null;
    }

    public boolean validateToken(String token) {
        return getBody(token).getExpiration().after(new Date());
    }

    public Authentication getAuthentication(String token) {
        Claims body = getBody(token);
        if (body.getExpiration().before(new Date()))
            throw ExpiredJwtException.EXCEPTION;

        UserDetails userDetails = getDetails(body);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public Claims getBody(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw ExpiredJwtException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidJwtException.EXCEPTION;
        }
    }

    public UserDetails getDetails(Claims body) {
        return authDetailsService.loadUserByUsername(body.getSubject());
    }
}
