package com.example.fileupload.exception;

import com.example.fileupload.error.ErrorCode;
import com.example.fileupload.error.Exception.BusinessException;

public class FileSetSizeException extends BusinessException {
    public static BusinessException Exception =
            new FileSetSizeException();
    private FileSetSizeException() {
        super(ErrorCode.FILE_NOT_SET_SIZE);
    }
}
