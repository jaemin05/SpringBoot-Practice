package com.example.oauth.exception;

import com.example.oauth.error.Exception.BusinessException;

public class EmailAlreadyExistsException extends BusinessException {
    public static BusinessException Exception =
            new EmailAlreadyExistsException();

    private EmailAlreadyExistsException(){
        super();
    }
}
