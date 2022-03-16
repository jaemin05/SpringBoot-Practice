package com.example.fcmpractice.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    INVALID_PARAMETER(400, "PARAMETER-400-1", "Invalid Parameter");

    private final int status;
    private final String code;
    private final String message;
}
