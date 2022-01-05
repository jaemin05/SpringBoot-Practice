package com.example.oauth.exception;

import com.example.oauth.error.ErrorCode;
import com.example.oauth.error.Exception.BusinessException;

public class ProviderTypeInvalidException extends BusinessException {

    public static BusinessException Exception =
            new ProviderTypeInvalidException();

    private ProviderTypeInvalidException() {
        super(ErrorCode.PROVIDER_TYPE_INVALID);
    }
}
