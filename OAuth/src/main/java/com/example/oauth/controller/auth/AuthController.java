package com.example.oauth.controller.auth;

import com.example.oauth.dto.Request.LoginRequest;
import com.example.oauth.dto.Request.SignUpRequest;
import com.example.oauth.dto.Request.TokenRequest;
import com.example.oauth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authService.signup(signUpRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenRequest> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/reissue")
    public ResponseEntity<String> reissue(@RequestBody TokenRequest tokenRequest){
        return ResponseEntity.ok(authService.reissue(tokenRequest));
    }
}
