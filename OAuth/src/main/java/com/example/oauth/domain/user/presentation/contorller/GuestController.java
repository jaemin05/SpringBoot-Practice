package com.example.oauth.domain.user.presentation.contorller;

import com.example.oauth.domain.user.presentation.dto.Request.LoginRequest;
import com.example.oauth.domain.user.presentation.dto.Request.SignUpRequest;
import com.example.oauth.domain.user.presentation.dto.response.TokenResponse;
import com.example.oauth.domain.user.service.GuestService;
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
@RequestMapping("/api/v1/auth/guest")
public class GuestController {
    private final GuestService guestService;

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid SignUpRequest signUpRequest) {
        guestService.signup(signUpRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.ok(guestService.login(loginRequest));
    }

    @PostMapping("/reissue")
    public TokenResponse reissue(@RequestHeader("X-Refresh-Token") String refresh) {
        return guestService.reissue(refresh);
    }
}