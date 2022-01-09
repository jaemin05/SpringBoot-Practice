package com.example.oauth.exception;

import com.example.oauth.error.Exception.BusinessException;

public class NotExpiredTokenYetException extends BusinessException {
    public static BusinessException Exception =
            new NotExpiredTokenYetException();

    private NotExpiredTokenYetException(){
        super();
    }
}
