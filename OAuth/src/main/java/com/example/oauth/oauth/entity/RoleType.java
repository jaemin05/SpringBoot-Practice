package com.example.oauth.oauth.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum RoleType {
    USER("ROLE_USER", "일반 사용자 권한"),
    ADMIN("ROLE_ADMIN", "관리자 권환"),
    GUEST("GUEST", "게스트 권한");

    private final String code;
    private final String Name;

    public static RoleType of(String code){
        return Arrays.stream(RoleType.values())
                .filter(roleType -> roleType.getCode().equals(code))
                .findAny()
                .orElse(GUEST);
    }
}
