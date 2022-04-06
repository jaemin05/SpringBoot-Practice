package com.example.oauth.global.exception;

import com.example.oauth.global.error.ErrorCode;
import com.example.oauth.global.error.Exception.OAuth2Exception;

public class NotExpiredTokenYetException extends OAuth2Exception {
    public static OAuth2Exception Exception =
            new NotExpiredTokenYetException();

    private NotExpiredTokenYetException(){
        super(ErrorCode.NOT_EXPIRED_TOKEN_YET);
    }
}
