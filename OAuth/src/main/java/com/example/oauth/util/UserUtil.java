package com.example.oauth.util;

import com.example.oauth.domain.refreshToken.UserRefreshTokenRepository;
import com.example.oauth.dto.response.TokenResponse;
import com.example.oauth.exception.InvalidTokenException;
import com.example.oauth.security.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class UserUtil {

    private final TokenProvider tokenProvider;
    private final UserRefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refreshTokenExp}")
    private Long refreshTokenExp;

    @Transactional
    public TokenResponse reissue(String refreshToken, String role){
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .filter(token -> tokenProvider.isRefreshToken(token.getRefreshToken()))
                .filter(token -> tokenProvider.checkRole(token.getRefreshToken(),role))
                .map(token -> {
                    String id = token.getUserId();
                    return new TokenResponse(
                            tokenProvider.generateAccessToken(id, role),
                            token.reissue(tokenProvider.generateRefreshToken(id, role), refreshTokenExp)
                    );
                })
                .orElseThrow(() -> InvalidTokenException.Exception);
    }
}
