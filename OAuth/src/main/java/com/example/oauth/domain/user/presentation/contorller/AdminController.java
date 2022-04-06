package com.example.oauth.domain.user.presentation.contorller;

import com.example.oauth.domain.user.presentation.dto.Request.LoginRequest;
import com.example.oauth.domain.user.presentation.dto.Request.SignUpRequest;
import com.example.oauth.domain.user.presentation.dto.response.TokenResponse;
import com.example.oauth.domain.user.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/admin")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid SignUpRequest signUpRequest) {
        adminService.signup(signUpRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.ok(adminService.login(loginRequest));
    }

    @PostMapping("/reissue")
    public TokenResponse reissue(@RequestHeader("X-Refresh-Token") String refresh) {
        return adminService.reissue(refresh);
    }
}