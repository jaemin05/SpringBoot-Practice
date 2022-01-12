package com.example.oauth.exception;

import com.example.oauth.error.ErrorCode;
import com.example.oauth.error.Exception.BusinessException;

public class ExpiredTokenException extends BusinessException {
    public static BusinessException Exception =
            new ExpiredTokenException();

    private ExpiredTokenException(){
        super(ErrorCode.Expired_Token);
    }
}
