package com.example.oauth.controller.auth;

import com.example.oauth.dto.Request.LoginRequest;
import com.example.oauth.dto.Request.SignUpRequest;
import com.example.oauth.dto.Request.TokenRequest;
import com.example.oauth.dto.response.TokenResponse;
import com.example.oauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid SignUpRequest signUpRequest){
        return ResponseEntity.ok(userService.signup(signUpRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenRequest> login(@RequestBody @Valid LoginRequest loginRequest){
        return ResponseEntity.ok(userService.login(loginRequest));
    }

    @PostMapping("/reissue")
    public TokenResponse reissue(@RequestHeader("X-Refresh-Token") String refresh){
        return userService.reissue(refresh);
    }
}
