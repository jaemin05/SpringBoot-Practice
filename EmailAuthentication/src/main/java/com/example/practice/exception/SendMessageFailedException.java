package com.example.practice.exception;

import com.example.practice.error.ErrorCode;
import com.example.practice.error.Exception.BusinessException;

public class SendMessageFailedException extends BusinessException {
    public SendMessageFailedException() {
        super(ErrorCode.SEND_MESSAGE_FAILED);
    }
}
