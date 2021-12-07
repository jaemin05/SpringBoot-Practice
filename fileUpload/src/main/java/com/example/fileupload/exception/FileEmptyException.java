package com.example.fileupload.exception;

import com.example.fileupload.error.ErrorCode;
import com.example.fileupload.error.Exception.BusinessException;

public class FileEmptyException extends BusinessException {
    public static BusinessException Exception =
            new FileEmptyException();

    private FileEmptyException() {
        super(ErrorCode.FILE_EMPTY);
    }
}
