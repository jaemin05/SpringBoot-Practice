package com.example.fcmpractice.domain.user.exception;

import com.example.fcmpractice.global.error.FCMPracticeException;

public class UserNotFoundException extends FCMPracticeException {
    public static UserNotFoundException EXCEPTION =
            new UserNotFoundException();

    private UserNotFoundException() {
        super();
    }
}
