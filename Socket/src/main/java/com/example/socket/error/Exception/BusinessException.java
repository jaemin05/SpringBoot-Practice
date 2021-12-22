package com.example.socket.error.Exception;

import com.example.socket.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Supplier;

@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;
}
