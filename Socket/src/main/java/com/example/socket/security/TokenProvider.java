package com.example.socket.security;

import com.example.socket.payload.response.JwtTokenResponse;
import com.example.socket.security.users.CustomUserDetails;
import com.example.socket.security.users.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenProvider {
    private final CustomUserDetailsService customUserDetailsService;

    @Value("${app.auth.accessTokenExpired}")
    private long ACCESS_TOKEN_EXPIRED;

    @Value("${app.auth.refreshTokenExpired}")
    private long REFRESH_TOKEN_EXPIRED;

    @Value("${app.auth.tokenSecurity}")
    private String TOKEN_SECRET;

    protected String init() {
        return Base64.getEncoder().encodeToString(TOKEN_SECRET.getBytes());
    }

    public JwtTokenResponse createToken(String userPk, String loginType){
        Claims claims = Jwts.claims().setSubject(userPk).setAudience(loginType).setIssuer("Admin");

        long now = (new Date()).getTime();
        return new JwtTokenResponse(
                Jwts.builder()
                        .setClaims(claims)
                        .setExpiration(new Date(now + ACCESS_TOKEN_EXPIRED))
                        .signWith(SignatureAlgorithm.HS256, init())
                        .compact(),

                Jwts.builder()
                        .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRED))
                        .signWith(SignatureAlgorithm.HS256, init())
                        .compact()
        );
    }

    public Authentication getAuthentication(String token) {
        CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(getUserSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getUserId(), userDetails.getAuthorities());
    }

    public String getUserSubject(String token) {
        return Jwts.parser().setSigningKey(init()).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validationToken(String token) {
        try{
            Date date = Jwts.parser().setSigningKey(init()).parseClaimsJws(token).getBody().getExpiration();
            return date.after(new Date(System.currentTimeMillis()));
        } catch (Exception e) {
            return false;
        }
    }
}
