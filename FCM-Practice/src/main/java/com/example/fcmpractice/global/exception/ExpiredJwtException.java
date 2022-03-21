package com.example.fcmpractice.global.exception;

import com.example.fcmpractice.global.error.ErrorCode;
import com.example.fcmpractice.global.error.FCMPracticeException;

public class ExpiredJwtException extends FCMPracticeException {
    public static ExpiredJwtException EXCEPTION =
            new ExpiredJwtException();

    private ExpiredJwtException() {
        super(ErrorCode.EXPIRED_JWT);
    }
}
