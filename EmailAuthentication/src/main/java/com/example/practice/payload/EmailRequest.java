package com.example.practice.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Getter
@NoArgsConstructor
public class EmailRequest {
    @Email
    private String email;
}
