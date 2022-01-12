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
    EMAIL_ALREADY_EXISTS(409, "Email Already Exists"),
    INVALID_TOKEN(409, "Invalid Token"),
    NOT_EXPIRED_TOKEN_YET(500, "Not Expired Token Yet"),
    USER_LOGOUT(500, "User Logout"),
    Expired_Token(401, "Expired Token"),
    Token_Valid_Failed(400,"Token_Valid_Failed");

    private final int status;
    private final String message;
}
