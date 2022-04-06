package com.example.oauth.global.exception;

import com.example.oauth.global.error.ErrorCode;
import com.example.oauth.global.error.Exception.OAuth2Exception;

public class TokenNotAuthoritiesException extends OAuth2Exception {
    public static OAuth2Exception Exception =
            new TokenNotAuthoritiesException();

    private TokenNotAuthoritiesException(){
        super(ErrorCode.TOKEN_NOT_AUTHORITIES);
    }
}
