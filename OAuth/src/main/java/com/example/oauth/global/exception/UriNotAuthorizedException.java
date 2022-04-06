package com.example.oauth.global.exception;

import com.example.oauth.global.error.ErrorCode;
import com.example.oauth.global.error.Exception.OAuth2Exception;

public class UriNotAuthorizedException extends OAuth2Exception {
    public static OAuth2Exception Exception =
            new UriNotAuthorizedException();

    private UriNotAuthorizedException(){
        super(ErrorCode.URI_NOT_AUTHORIZED);
    }
}
