package com.example.fcmpractice.domain.user.service;

import com.example.fcmpractice.domain.auth.presentation.dto.TokenResponse;
import com.example.fcmpractice.domain.user.presentation.dto.LoginRequest;
import com.example.fcmpractice.domain.user.presentation.dto.SignupRequest;

public interface UserService {
    void signup(SignupRequest signupRequest);
    TokenResponse login(LoginRequest loginRequest);
}
