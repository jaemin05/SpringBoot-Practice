package com.example.fileupload.exception;

import com.example.fileupload.error.ErrorCode;
import com.example.fileupload.error.Exception.BusinessException;

public class FileConvertException extends BusinessException {
    public static BusinessException Exception =
            new FileConvertException();

    private FileConvertException() {
        super(ErrorCode.FILE_CONVERT_FAILED);
    }
}
