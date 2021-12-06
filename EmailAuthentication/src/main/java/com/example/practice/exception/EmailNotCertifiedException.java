package com.example.practice.exception;

import com.example.practice.error.ErrorCode;
import com.example.practice.error.Exception.BusinessException;

public class EmailNotCertifiedException extends BusinessException {
    public EmailNotCertifiedException(){
        super(ErrorCode.EMAIL_NOT_CERTIFIED);
    }
}
