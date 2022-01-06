package com.example.oauth.exception;

import com.example.oauth.error.ErrorCode;
import com.example.oauth.error.Exception.BusinessException;

public class TokenNotAuthoritiesException extends BusinessException {
    public static BusinessException Exception =
            new TokenNotAuthoritiesException();

    private TokenNotAuthoritiesException(){
        super(ErrorCode.TOKEN_NOT_AUTHORITIES);
    }
}
