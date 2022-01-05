package com.example.oauth.exception;

import com.example.oauth.error.ErrorCode;
import com.example.oauth.error.Exception.BusinessException;

public class UserNotFound extends BusinessException {
    public static BusinessException Exception =
            new UserNotFound();

    private UserNotFound(){
        super(ErrorCode.USER_NOT_FOUND);
    }
}
