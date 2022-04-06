package com.example.oauth.global.error.Exception;

import com.example.oauth.global.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OAuth2Exception extends RuntimeException{
    private final ErrorCode errorCode;
}
