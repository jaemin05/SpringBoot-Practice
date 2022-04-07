package com.example.oauth.domain.auth.service;

import com.example.oauth.domain.auth.domain.RefreshToken;
import com.example.oauth.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.oauth.domain.user.presentation.dto.response.TokenResponse;
import com.example.oauth.global.exception.InvalidTokenException;
import com.example.oauth.global.exception.RefreshTokenNotFoundException;
import com.example.oauth.global.security.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final TokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public TokenResponse execute(String refreshToken) {

        if (!jwtTokenProvider.getBody(refreshToken).get("typ").equals("refresh"))
            throw InvalidTokenException.Exception;

        RefreshToken updateRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> RefreshTokenNotFoundException.Exception);

        String newRefreshToken = jwtTokenProvider.generateRefreshToken(updateRefreshToken.getId());
        updateRefreshToken.update(newRefreshToken);

        String newAccessToken = jwtTokenProvider.generateAccessToken(updateRefreshToken.getId());

        return TokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }
}
