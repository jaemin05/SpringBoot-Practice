package com.example.fcmpractice.global.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    INVALID_PARAMETER(400, "PARAMETER-400-1", "Invalid Parameter"),
    PASSWORD_NOT_CORRECT(400, "AUTH-400-1", "Password Not Correct"),

    EXPIRED_JWT(401, "JWT-401-1", "Expired Jwt"),
    INVALID_JWT(401, "JWT-401-2", "Invalid Jwt"),
    INVALID_USER(401, "USER-401-1", "Invalid User"),

    USER_NOT_FOUND(404,"USER-404-1", "User NotFound"),
    JWT_NOT_FOUND(404, "JWT-404-3", "Jwt Not Found"),
    NOTICE_NOT_FOUND(404, "NOTICE-404-1", "Notice Not Found"),
    NOTIFICATION_NOT_FOUND(404, "NOTIFICATION-404-1", "Notification Not Found"),

    ACCOUNT_ID_ALREADY_EXISTS(409, "USER-409-1", "AccountId Already Exists");

    private final int status;
    private final String code;
    private final String message;
}
