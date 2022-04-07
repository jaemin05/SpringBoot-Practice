package com.example.oauth.domain.auth.presentation;

import com.example.oauth.domain.auth.service.AuthService;
import com.example.oauth.domain.user.presentation.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public TokenResponse userTokenRefresh(@RequestHeader("Refresh-Token") String refreshToken) {
        return authService.execute(refreshToken);
    }
}
