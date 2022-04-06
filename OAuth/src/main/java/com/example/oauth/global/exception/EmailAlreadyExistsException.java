package com.example.oauth.global.exception;

import com.example.oauth.global.error.ErrorCode;
import com.example.oauth.global.error.Exception.OAuth2Exception;

public class EmailAlreadyExistsException extends OAuth2Exception {
    public static OAuth2Exception Exception =
            new EmailAlreadyExistsException();

    private EmailAlreadyExistsException(){
        super(ErrorCode.EMAIL_ALREADY_EXISTS);
    }
}
