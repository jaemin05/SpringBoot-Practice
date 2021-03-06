package com.example.practice.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PasswordRequest {
    private String email;
    private String password;
    private String change;
}
