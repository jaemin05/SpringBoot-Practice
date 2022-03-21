package com.example.fcmpractice.global.exception;

import com.example.fcmpractice.global.error.ErrorCode;
import com.example.fcmpractice.global.error.FCMPracticeException;

public class JwtNotFoundException extends FCMPracticeException {
    public static JwtNotFoundException EXCEPTION =
            new JwtNotFoundException();

    private JwtNotFoundException() {
        super(ErrorCode.JWT_NOT_FOUND);
    }
}
