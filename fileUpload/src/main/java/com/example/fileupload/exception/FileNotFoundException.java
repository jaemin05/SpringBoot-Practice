package com.example.fileupload.exception;

import com.example.fileupload.error.ErrorCode;
import com.example.fileupload.error.Exception.BusinessException;

public class FileNotFoundException extends BusinessException {
    public static BusinessException EXCEPTION =
            new FileNotFoundException();

    private FileNotFoundException() {
        super(ErrorCode.File_NOT_FOUND);
    }
}
