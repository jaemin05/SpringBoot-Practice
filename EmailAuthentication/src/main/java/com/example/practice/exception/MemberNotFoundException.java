package com.example.practice.exception;

import com.example.practice.error.ErrorCode;
import com.example.practice.error.Exception.BusinessException;

public class MemberNotFoundException extends BusinessException {
    public static BusinessException Exception =
            new MemberNotFoundException();

    private MemberNotFoundException() {
        super(ErrorCode.MEMBER_NOT_FOUND);
    }
}
