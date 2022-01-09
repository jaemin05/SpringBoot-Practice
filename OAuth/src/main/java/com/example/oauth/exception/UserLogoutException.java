package com.example.oauth.exception;

import com.example.oauth.error.ErrorCode;
import com.example.oauth.error.Exception.BusinessException;

public class UserLogoutException extends BusinessException {
    public static BusinessException Exception =
            new UserLogoutException();

    private UserLogoutException(){
        super(ErrorCode.USER_LOGOUT);
    }
}
