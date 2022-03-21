package com.example.fcmpractice.domain.user.exception;

import com.example.fcmpractice.global.error.ErrorCode;
import com.example.fcmpractice.global.error.FCMPracticeException;

public class AccountIdAlreadyExistsException extends FCMPracticeException {
    public static FCMPracticeException EXCEPTION =
            new AccountIdAlreadyExistsException();

    private AccountIdAlreadyExistsException() {
        super(ErrorCode.ACCOUNT_ID_ALREADY_EXISTS);
    }
}
