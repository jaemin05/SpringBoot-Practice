package com.example.practice.exception;

import com.example.practice.error.ErrorCode;
import com.example.practice.error.Exception.BusinessException;

public class PasswordNotCorrectException extends BusinessException {
    public PasswordNotCorrectException() {
        super(ErrorCode.PASSWORD_NOT_CORRECT);
    }
}
