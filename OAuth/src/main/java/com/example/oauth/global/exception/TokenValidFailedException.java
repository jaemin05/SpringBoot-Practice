package com.example.oauth.global.exception;

import com.example.oauth.global.error.ErrorCode;
import com.example.oauth.global.error.Exception.OAuth2Exception;

public class TokenValidFailedException extends OAuth2Exception {
    public static OAuth2Exception Exception =
            new TokenValidFailedException();

    private TokenValidFailedException(){
        super(ErrorCode.Token_Valid_Failed);
    }
}
