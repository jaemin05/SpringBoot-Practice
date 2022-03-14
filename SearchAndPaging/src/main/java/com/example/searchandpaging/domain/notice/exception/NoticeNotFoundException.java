package com.example.searchandpaging.domain.notice.exception;

import com.example.searchandpaging.global.error.exception.CustomException;
import com.example.searchandpaging.global.error.exception.ErrorCode;

public class NoticeNotFoundException extends CustomException {
    public static NoticeNotFoundException EXCEPTION =
            new NoticeNotFoundException();

    private NoticeNotFoundException() {
        super(ErrorCode.NOTICE_NOT_FOUND);
    }
}
