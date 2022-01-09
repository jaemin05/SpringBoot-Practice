package com.example.oauth.exception;

import com.example.oauth.error.ErrorCode;
import com.example.oauth.error.Exception.BusinessException;

public class UserNotFoundException extends BusinessException {
    public static BusinessException Exception =
            new UserNotFoundException();

    private UserNotFoundException(){
        super(ErrorCode.USER_NOT_FOUND);
    }
}
