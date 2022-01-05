package com.example.oauth.exception;

import com.example.oauth.error.Exception.BusinessException;

public class OAuthProviderMissMatchException extends RuntimeException{
    private OAuthProviderMissMatchException(String message){
        super(message);
    }
}
