package com.example.oauth.exception;

import com.example.oauth.error.ErrorCode;
import com.example.oauth.error.Exception.BusinessException;

public class InvalidAccessTokenException extends BusinessException {
    public static BusinessException Exception =
            new InvalidAccessTokenException();

    private InvalidAccessTokenException(){
        super(ErrorCode.INVALID_ACCESS_TOKEN);
    }
}
