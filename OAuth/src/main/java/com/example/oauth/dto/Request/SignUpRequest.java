package com.example.oauth.dto.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequest {
    private String name;
    private String email;
    private String password;
}
