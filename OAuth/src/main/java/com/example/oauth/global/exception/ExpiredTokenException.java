package com.example.oauth.global.exception;

import com.example.oauth.global.error.ErrorCode;
import com.example.oauth.global.error.Exception.OAuth2Exception;

public class ExpiredTokenException extends OAuth2Exception {
    public static OAuth2Exception Exception =
            new ExpiredTokenException();

    private ExpiredTokenException(){
        super(ErrorCode.Expired_Token);
    }
}
