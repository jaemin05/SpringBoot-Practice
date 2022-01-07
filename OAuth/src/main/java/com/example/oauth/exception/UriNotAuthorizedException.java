package com.example.oauth.exception;

import com.example.oauth.error.ErrorCode;
import com.example.oauth.error.Exception.BusinessException;

public class UriNotAuthorizedException extends BusinessException {
    public static BusinessException Exception =
            new UriNotAuthorizedException();

    private UriNotAuthorizedException(){
        super(ErrorCode.URI_NOT_AUTHORIZED);
    }
}
