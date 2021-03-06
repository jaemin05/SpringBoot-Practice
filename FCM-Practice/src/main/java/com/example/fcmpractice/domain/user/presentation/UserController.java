package com.example.fcmpractice.domain.user.presentation;

import com.example.fcmpractice.domain.auth.presentation.dto.TokenResponse;
import com.example.fcmpractice.domain.user.presentation.dto.LoginRequest;
import com.example.fcmpractice.domain.user.presentation.dto.SignupRequest;
import com.example.fcmpractice.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public void signup(@RequestBody @Valid SignupRequest signupRequest) {
        userService.signup(signupRequest);
    }

    @PatchMapping
    public TokenResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }
}
