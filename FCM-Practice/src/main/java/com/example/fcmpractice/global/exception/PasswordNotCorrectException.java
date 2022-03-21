package com.example.fcmpractice.global.exception;

import com.example.fcmpractice.global.error.ErrorCode;
import com.example.fcmpractice.global.error.FCMPracticeException;

public class PasswordNotCorrectException extends FCMPracticeException {
    public static PasswordNotCorrectException EXCEPTION =
            new PasswordNotCorrectException();

    private PasswordNotCorrectException() {
        super(ErrorCode.PASSWORD_NOT_CORRECT);
    }
}
