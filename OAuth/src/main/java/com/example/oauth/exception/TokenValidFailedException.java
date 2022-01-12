package com.example.oauth.exception;

import com.example.oauth.error.ErrorCode;
import com.example.oauth.error.Exception.BusinessException;

public class TokenValidFailedException extends BusinessException {
    public static BusinessException Exception =
            new TokenValidFailedException();

    private TokenValidFailedException(){
        super(ErrorCode.Token_Valid_Failed);
    }
}
