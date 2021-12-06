package com.example.practice.exception;

import com.example.practice.error.ErrorCode;
import com.example.practice.error.Exception.BusinessException;

public class CodeAlreadyExpiredException extends BusinessException {

    public static BusinessException EXCEPTION =
            new CodeAlreadyExpiredException();

    private CodeAlreadyExpiredException() {
        super(ErrorCode.CODE_ALREADY_EXPIRED);
    }
}
