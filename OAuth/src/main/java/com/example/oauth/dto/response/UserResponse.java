package com.example.oauth.dto.response;

import com.example.oauth.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {
    private final User user;
}
