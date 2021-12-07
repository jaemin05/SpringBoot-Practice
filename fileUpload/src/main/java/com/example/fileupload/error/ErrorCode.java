package com.example.fileupload.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    File_NOT_FOUND(500, "File Not Found"),
    FILE_EMPTY(409, "File is Empty"),
    FILE_NOT_STORE(500, "File Not Store");

    private int statues;
    private String message;
}
