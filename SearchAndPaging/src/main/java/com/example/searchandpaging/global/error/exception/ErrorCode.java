package com.example.searchandpaging.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    NOTICE_NOT_FOUND(404, "Notice-404-1", "Notice Not Found"),

    FORBIDDEN(403, "COMMON-403-1", "Forbidden");

    private final int status;
    private final String code;
    private final String message;
}
