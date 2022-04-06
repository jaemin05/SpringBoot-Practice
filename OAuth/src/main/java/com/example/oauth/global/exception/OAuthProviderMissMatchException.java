package com.example.oauth.global.exception;

public class OAuthProviderMissMatchException extends RuntimeException{
    private OAuthProviderMissMatchException(String message){
        super(message);
    }
}
