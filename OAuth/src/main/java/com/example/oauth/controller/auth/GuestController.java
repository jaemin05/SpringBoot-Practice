package com.example.oauth.controller.auth;

import com.example.oauth.dto.Request.LoginRequest;
import com.example.oauth.dto.Request.SignUpRequest;
import com.example.oauth.dto.response.TokenResponse;
import com.example.oauth.service.auth.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guest")
public class GuestController {
    private final GuestService guestService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid SignUpRequest signUpRequest){
        return ResponseEntity.ok(guestService.signup(signUpRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest loginRequest){
        return ResponseEntity.ok(guestService.login(loginRequest));
    }

    @PostMapping("/reissue")
    public TokenResponse reissue(@RequestHeader("X-Refresh-Token") String refresh){
        return guestService.reissue(refresh);
    }
}