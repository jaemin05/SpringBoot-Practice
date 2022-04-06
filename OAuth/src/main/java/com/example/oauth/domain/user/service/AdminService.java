package com.example.oauth.domain.user.service;

import com.example.oauth.domain.refreshToken.UserRefreshToken;
import com.example.oauth.domain.refreshToken.UserRefreshTokenRepository;
import com.example.oauth.domain.user.domain.AuthProvider;
import com.example.oauth.domain.user.domain.User;
import com.example.oauth.domain.user.domain.repository.UserRepository;
import com.example.oauth.domain.user.presentation.dto.Request.LoginRequest;
import com.example.oauth.domain.user.presentation.dto.Request.SignUpRequest;
import com.example.oauth.domain.user.presentation.dto.response.TokenResponse;
import com.example.oauth.global.config.JwtProperties;
import com.example.oauth.global.exception.RefreshTokenNotFoundException;
import com.example.oauth.global.security.token.TokenProvider;
import com.example.oauth.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final JwtProperties jwtProperties;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final UserRefreshTokenRepository userRefreshTokenRepository;
    private final UserUtil userUtil;
    private final String role = "admin";

    @Transactional
    public void signup(SignUpRequest signUpRequest) {
        userRepository.save(User.builder()
                .name(signUpRequest.getName())
                .email(signUpRequest.getEmail())
                .authProvider(AuthProvider.LOCAL)
                .build());
    }

    @Transactional
    public TokenResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), null
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String userId = loginRequest.getEmail();
        String accessToken = tokenProvider.generateAccessToken(
                userId,
                role
        );

        String refreshToken = tokenProvider.generateRefreshToken(
                jwtProperties.getAuth().getSecretToken(),
                role
        );

        UserRefreshToken userRefreshToken = userRefreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> RefreshTokenNotFoundException.Exception);
        if (userRefreshToken == null) {
            userRefreshToken = new UserRefreshToken(userId, refreshToken, jwtProperties.getAuth().getRefreshTokenExp());
            userRefreshTokenRepository.save(userRefreshToken);
        } else {
            userRefreshToken.reissue(refreshToken, jwtProperties.getAuth().getRefreshTokenExp());
        }
        return new TokenResponse(accessToken, refreshToken);
    }

    ;

    @Transactional
    public TokenResponse reissue(String refreshToken) {
        return userUtil.reissue(refreshToken, role);
    }
}
