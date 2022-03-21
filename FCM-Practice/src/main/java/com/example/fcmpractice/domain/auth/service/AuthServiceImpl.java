package com.example.fcmpractice.domain.auth.service;

import com.example.fcmpractice.domain.auth.domain.RefreshToken;
import com.example.fcmpractice.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.fcmpractice.domain.auth.presentation.dto.TokenResponse;
import com.example.fcmpractice.global.exception.InvalidJwtException;
import com.example.fcmpractice.global.exception.JwtNotFoundException;
import com.example.fcmpractice.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public TokenResponse execute(String refreshToken) {

        if (!jwtTokenProvider.getBody(refreshToken).get("typ").equals("refresh"))
            throw InvalidJwtException.EXCEPTION;

        RefreshToken updateRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> JwtNotFoundException.EXCEPTION);

        String newRefreshToken = jwtTokenProvider.generateRefreshToken(updateRefreshToken.getId());
        updateRefreshToken.update(newRefreshToken);

        String newAccessToken = jwtTokenProvider.generateAccessToken(updateRefreshToken.getId());

        return TokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }
}
