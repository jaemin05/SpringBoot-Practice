package com.example.practice.exception;

import com.example.practice.error.ErrorCode;
import com.example.practice.error.Exception.BusinessException;

public class MemberEmailAlreadyExistsException extends BusinessException {
    public MemberEmailAlreadyExistsException() {
        super(ErrorCode.MEMBER_EMAIL_ALREADY_EXISTS);
    }
}
