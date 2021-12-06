package com.example.practice.exception;

import com.example.practice.error.ErrorCode;
import com.example.practice.error.Exception.BusinessException;

public class CodeNotCorrectException extends BusinessException {

    public static BusinessException EXCEPTION =
            new CodeNotCorrectException();

    private CodeNotCorrectException() {
        super(ErrorCode.CODE_NOT_CORRECT);
    }
}
