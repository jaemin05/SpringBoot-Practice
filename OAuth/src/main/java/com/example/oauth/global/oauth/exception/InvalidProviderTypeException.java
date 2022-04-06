package com.example.oauth.global.oauth.exception;

import com.example.oauth.global.error.ErrorCode;
import com.example.oauth.global.error.Exception.OAuth2Exception;

public class InvalidProviderTypeException extends OAuth2Exception {
    public static InvalidProviderTypeException Exception =
            new InvalidProviderTypeException();

    private InvalidProviderTypeException() {
        super(ErrorCode.INVALID_PROVIDER_TYPE);
    }
}
