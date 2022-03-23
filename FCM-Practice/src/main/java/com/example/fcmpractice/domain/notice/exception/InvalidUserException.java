package com.example.fcmpractice.domain.notice.exception;

import com.example.fcmpractice.global.error.ErrorCode;
import com.example.fcmpractice.global.error.FCMPracticeException;

public class InvalidUserException extends FCMPracticeException {
    public static InvalidUserException EXCEPTION =
            new InvalidUserException();

    private InvalidUserException() {
        super(ErrorCode.INVALID_USER);
    }
}
