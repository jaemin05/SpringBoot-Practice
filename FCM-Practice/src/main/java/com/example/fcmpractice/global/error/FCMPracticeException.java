package com.example.fcmpractice.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FCMPracticeException extends RuntimeException{

    private final ErrorCode errorCode;
}
