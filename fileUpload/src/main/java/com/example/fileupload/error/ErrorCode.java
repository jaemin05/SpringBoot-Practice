package com.example.fileupload.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    File_NOT_FOUND(500, "File Not Found"),
    FILE_EMPTY(409, "File is Empty"),
    FILE_NOT_STORE(500, "File Not Store"),
    FILE_NOT_SET_SIZE(401, "File Not Set Size"),
    FILE_CONVERT_FAILED(409, "File Convert Failed");

    private int statues;
    private String message;
}
