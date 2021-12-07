package com.example.fileupload.exception;

import com.example.fileupload.error.ErrorCode;
import com.example.fileupload.error.Exception.BusinessException;

public class FileNotStoreException extends BusinessException {
    public static BusinessException Exception =
            new FileNotStoreException();
    private FileNotStoreException() {
        super(ErrorCode.FILE_NOT_STORE);
    }
}
