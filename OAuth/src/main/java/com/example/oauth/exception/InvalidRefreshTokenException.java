package com.example.oauth.exception;

import com.example.oauth.error.ErrorCode;
import com.example.oauth.error.Exception.BusinessException;

public class InvalidRefreshTokenException extends BusinessException {
    public static BusinessException Exception =
            new InvalidRefreshTokenException();

    private InvalidRefreshTokenException() {
        super(ErrorCode.INVALID_REFRESH_TOKEN);
    }
}
