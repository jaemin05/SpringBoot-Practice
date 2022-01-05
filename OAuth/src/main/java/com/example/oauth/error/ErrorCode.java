package com.example.oauth.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    PROVIDER_TYPE_INVALID(409, "Provider Type Invalid"),
    USER_NOT_FOUND(404, "User Not Found");

    private final int status;
    private final String message;
}
