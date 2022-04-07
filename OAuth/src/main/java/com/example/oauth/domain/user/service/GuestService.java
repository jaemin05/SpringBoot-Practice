package com.example.oauth.domain.user.service;

import com.example.oauth.domain.user.domain.ProviderType;
import com.example.oauth.domain.user.domain.Role;
import com.example.oauth.domain.user.domain.User;
import com.example.oauth.domain.user.domain.repository.UserRepository;
import com.example.oauth.domain.user.presentation.dto.Request.LoginRequest;
import com.example.oauth.domain.user.presentation.dto.Request.SignUpRequest;
import com.example.oauth.domain.user.presentation.dto.response.TokenResponse;
import com.example.oauth.global.exception.UserNotFoundException;
import com.example.oauth.global.security.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class GuestService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public void signup(SignUpRequest signupRequest) {
        userRepository.save(
                User.builder()
                        .name(signupRequest.getName())
                        .email(signupRequest.getEmail())
                        .role(Role.GUEST)
                        .providerType(ProviderType.LOCAL)
                        .build()
        );
    }

    @Transactional
    public TokenResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> UserNotFoundException.Exception);

        String access = tokenProvider.generateAccessToken(user.getEmail());
        String refresh = tokenProvider.generateRefreshToken(user.getEmail());

        return TokenResponse.builder()
                .accessToken(access)
                .refreshToken(refresh)
                .build();
    }
}
