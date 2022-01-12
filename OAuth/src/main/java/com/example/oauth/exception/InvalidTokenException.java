package com.example.oauth.exception;

import com.example.oauth.error.ErrorCode;
import com.example.oauth.error.Exception.BusinessException;

public class InvalidTokenException extends BusinessException {
    public static BusinessException Exception =
            new InvalidTokenException();

    private InvalidTokenException(){
        super(ErrorCode.INVALID_TOKEN);
    }
}
