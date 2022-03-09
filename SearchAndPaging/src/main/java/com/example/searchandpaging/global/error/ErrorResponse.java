package com.example.searchandpaging.global.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
//access = AccessLevel.PRIVATE: 외부에서 접근 못하게 막는다.
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ErrorResponse {
    private final int status;
    private final String message;
}
