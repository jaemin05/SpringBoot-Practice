package com.example.oauth.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    PROVIDER_TYPE_INVALID(409, "Provider Type Invalid"),
    USER_NOT_FOUND(404, "User Not Found"),
    TOKEN_NOT_AUTHORITIES(403, "Token Not Authorities"),
    URI_NOT_AUTHORIZED(403, "Uri Not Authorized"),
    REFRESH_TOKEN_NOT_FOUND(404, "Refresh Token Not Found"),
    EMAIL_ALREADY_EXISTS(409, "Email Already Exists");

    private final int status;
    private final String message;
}
