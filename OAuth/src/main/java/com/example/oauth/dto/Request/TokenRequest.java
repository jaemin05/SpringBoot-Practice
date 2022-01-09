package com.example.oauth.dto.Request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenRequest {
    private String accessToken;
    private String refreshToken;

    @Builder
    public TokenRequest(String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken  = refreshToken;
    }
}
