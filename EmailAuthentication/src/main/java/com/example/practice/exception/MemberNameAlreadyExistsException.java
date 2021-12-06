package com.example.practice.exception;

import com.example.practice.error.ErrorCode;
import com.example.practice.error.Exception.BusinessException;

public class MemberNameAlreadyExistsException extends BusinessException {
    public MemberNameAlreadyExistsException(){
        super(ErrorCode.MEMBER_NAME_ALREADY_EXISTS);
    }
}
