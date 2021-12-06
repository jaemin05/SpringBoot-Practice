package com.example.practice.exception;

import com.example.practice.error.ErrorCode;
import com.example.practice.error.Exception.BusinessException;

public class PasswordNotFoundException extends BusinessException {
    public PasswordNotFoundException(){
        super(ErrorCode.PASSWORD_NOT_FOUND);
    }
}
