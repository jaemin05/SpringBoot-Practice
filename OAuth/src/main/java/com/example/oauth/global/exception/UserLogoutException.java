package com.example.oauth.global.exception;

import com.example.oauth.global.error.ErrorCode;
import com.example.oauth.global.error.Exception.OAuth2Exception;

public class UserLogoutException extends OAuth2Exception {
    public static OAuth2Exception Exception =
            new UserLogoutException();

    private UserLogoutException(){
        super(ErrorCode.USER_LOGOUT);
    }
}
