package com.example.socket.exception;

import com.example.socket.error.ErrorCode;
import com.example.socket.error.Exception.BusinessException;

public class MemberNotFoundException extends BusinessException {
    public static BusinessException Exception =
            new MemberNotFoundException();

    private MemberNotFoundException() {
        super(ErrorCode.MEMBER_NOT_FOUND);
    }
}
