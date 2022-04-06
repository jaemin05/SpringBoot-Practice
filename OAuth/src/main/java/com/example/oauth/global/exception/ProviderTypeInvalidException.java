package com.example.oauth.global.exception;

import com.example.oauth.global.error.ErrorCode;
import com.example.oauth.global.error.Exception.OAuth2Exception;

public class ProviderTypeInvalidException extends OAuth2Exception {

    public static OAuth2Exception Exception =
            new ProviderTypeInvalidException();

    private ProviderTypeInvalidException() {
        super(ErrorCode.PROVIDER_TYPE_INVALID);
    }
}
