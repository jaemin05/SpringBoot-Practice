package com.example.fcmpractice.domain.auth.service;

import com.example.fcmpractice.domain.auth.presentation.dto.TokenResponse;

public interface AuthService {
    public TokenResponse execute(String refreshToken);
}
