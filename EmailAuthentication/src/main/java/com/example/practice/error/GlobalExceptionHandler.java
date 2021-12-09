package com.example.practice.error;

import com.example.practice.error.Exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //모든 @Controller에 대한 전역적으로 발생할 수 있는 예외를 잡아서 처리할 수 있다.
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class) //BusinessException : 요구사항에 맞지 않을 경우 발생시키는 Exception
    public ResponseEntity<ErrorResponse> handleException(BusinessException e) {
        final ErrorCode errorCode = e.getErrorCode();
        return new ResponseEntity<>(new ErrorResponse(errorCode.getMessage()), HttpStatus.valueOf(errorCode.getStatues()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    //Reqeust Body와 Valid 어노테이션을 통해서 client로 부터 가져온 정보들을 검증실패할 때
    public ResponseEntity<ErrorResponse> handleValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(
                new ErrorResponse(e.getBindingResult().getAllErrors().get(0).getDefaultMessage()), HttpStatus.BAD_REQUEST);
    }


}
