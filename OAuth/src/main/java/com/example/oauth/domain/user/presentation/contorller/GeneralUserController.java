package com.example.oauth.domain.user.presentation.contorller;

import com.example.oauth.domain.user.presentation.dto.Request.LoginRequest;
import com.example.oauth.domain.user.presentation.dto.Request.SignUpRequest;
import com.example.oauth.domain.user.presentation.dto.response.TokenResponse;
import com.example.oauth.domain.user.service.GeneralUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/generalUser")
public class GeneralUserController {
    private final GeneralUserService userService;

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid SignUpRequest signUpRequest) {
        userService.signup(signUpRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.login(loginRequest));
    }
}
