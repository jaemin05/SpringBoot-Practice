package com.example.socket.payload.response;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtTokenResponse {
    private String accessToken;
    private String refreshToken;
}
