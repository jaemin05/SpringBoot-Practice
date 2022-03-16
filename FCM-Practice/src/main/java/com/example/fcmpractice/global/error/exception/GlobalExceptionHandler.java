package com.example.fcmpractice.global.error.exception;

import com.example.fcmpractice.global.error.ErrorCode;
import com.example.fcmpractice.global.error.FCMPracticeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FCMPracticeException.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(FCMPracticeException e) {

        final ErrorCode errorCode = e.getErrorCode();
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .status(errorCode.getStatus())
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build(),
                HttpStatus.valueOf(errorCode.getStatus())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .status(ErrorCode.INVALID_PARAMETER.getStatus())
                        .code(ErrorCode.INVALID_PARAMETER.getCode())
                        .message(e.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }
}
