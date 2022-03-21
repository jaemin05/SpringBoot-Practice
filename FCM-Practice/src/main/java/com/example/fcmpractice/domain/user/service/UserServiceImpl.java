package com.example.fcmpractice.domain.user.service;

import com.example.fcmpractice.domain.auth.presentation.dto.TokenResponse;
import com.example.fcmpractice.domain.user.domain.User;
import com.example.fcmpractice.domain.user.domain.repository.UserRepository;
import com.example.fcmpractice.domain.user.facade.UserFacade;
import com.example.fcmpractice.domain.user.presentation.dto.LoginRequest;
import com.example.fcmpractice.domain.user.presentation.dto.SignupRequest;
import com.example.fcmpractice.global.exception.PasswordNotCorrectException;
import com.example.fcmpractice.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void signup(SignupRequest signupRequest) {
        userFacade.accountIdAlreadyExists(signupRequest.getAccountId());

        userRepository.save(
                User.builder()
                        .accountId(signupRequest.getAccountId())
                        .password(passwordEncoder.encode(signupRequest.getPassword()))
                        .build());
    }

    @Override
    @Transactional
    public TokenResponse login(LoginRequest loginRequest) {
        User user = userFacade.getByAccountId(loginRequest.getAccountId());

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
            throw PasswordNotCorrectException.EXCEPTION;

        user.setDeviceToken(loginRequest.getDevice_token());

        String access = jwtTokenProvider.generateAccessToken(user.getAccountId());
        String refresh = jwtTokenProvider.generateRefreshToken(user.getAccountId());

        return TokenResponse.builder()
                .accessToken(access)
                .refreshToken(refresh)
                .build();
    }

}
