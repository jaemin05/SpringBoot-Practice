package com.example.oauth.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST", "게스트"),
    USER("ROLE_USER", "일반 사용자"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String value;

    public static Role of(String code){
        return Arrays.stream(Role.values())
                .filter(role -> role.getKey().equals(code))
                .findAny()
                .orElse(GUEST);
    }
}
