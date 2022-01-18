package com.example.oauth.controller.auth;

import com.example.oauth.dto.Request.LoginRequest;
import com.example.oauth.dto.Request.SignUpRequest;
import com.example.oauth.dto.response.TokenResponse;
import com.example.oauth.service.auth.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/admin")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid SignUpRequest signUpRequest){
        return ResponseEntity.ok(adminService.signup(signUpRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest loginRequest){
        return ResponseEntity.ok(adminService.login(loginRequest));
    }

    @PostMapping("/reissue")
    public TokenResponse reissue(@RequestHeader("X-Refresh-Token") String refresh){
        return adminService.reissue(refresh);
    }
}