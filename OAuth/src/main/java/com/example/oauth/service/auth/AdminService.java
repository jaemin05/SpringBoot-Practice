package com.example.oauth.service.auth;

import com.example.oauth.config.JwtProperties;
import com.example.oauth.domain.refreshToken.UserRefreshToken;
import com.example.oauth.domain.refreshToken.UserRefreshTokenRepository;
import com.example.oauth.domain.user.*;
import com.example.oauth.dto.Request.LoginRequest;
import com.example.oauth.dto.Request.SignUpRequest;
import com.example.oauth.dto.Request.TokenRequest;
import com.example.oauth.dto.response.TokenResponse;
import com.example.oauth.exception.*;
import com.example.oauth.security.token.TokenProvider;
import com.example.oauth.util.UserUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AdminService {
    private final JwtProperties jwtProperties;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final UserRefreshTokenRepository userRefreshTokenRepository;
    private final UserUtil userUtil;
    private final String role = "admin";

    @Transactional
    public String signup(SignUpRequest signUpRequest){
        if(userRepository.existsByEmail(signUpRequest.getEmail())){
            throw EmailAlreadyExistsException.Exception;
        }

        userRepository.save(User.builder()
                .name(signUpRequest.getName())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .authProvider(AuthProvider.LOCAL)
                .build());

        return "성공적으로 계졍이 생성되었습니다.";
    }

    @Transactional
    public TokenRequest login(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
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
        if(userRefreshToken == null){
            userRefreshToken = new UserRefreshToken(userId, refreshToken, jwtProperties.getAuth().getRefreshTokenExp());
            userRefreshTokenRepository.save(userRefreshToken);
        } else {
            userRefreshToken.reissue(refreshToken, jwtProperties.getAuth().getRefreshTokenExp());
        }
        return new TokenRequest(accessToken, refreshToken);
    };

    @Transactional
    public TokenResponse reissue(String refreshToken){
        return userUtil.reissue(refreshToken, role);
    }
}
