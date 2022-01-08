package com.example.oauth.security.oauth.user;

import com.example.oauth.domain.user.AuthProvider;
import com.example.oauth.exception.ProviderTypeInvalidException;
import com.example.oauth.security.oauth.user.impl.FaceBookOAuthUserInfo;
import com.example.oauth.security.oauth.user.impl.GoogleOAuthUserInfo;
import com.example.oauth.security.oauth.user.impl.KakaoOAuthUserInfo;
import com.example.oauth.security.oauth.user.impl.NaverOAuthUserInfo;

import java.util.Map;

public class OAuthUserInfoFactory {
    public static OAuthUserInfo getOAuthUserInfo(AuthProvider providerType, Map<String, Object> attributes) {
        switch (providerType){
            case GOOGLE: return new GoogleOAuthUserInfo(attributes);
            case FACEBOOK: return new FaceBookOAuthUserInfo(attributes);
            case KAKAO: return new KakaoOAuthUserInfo(attributes);
            case NAVER: return new NaverOAuthUserInfo(attributes);
            default: throw ProviderTypeInvalidException.Exception;
        }
    }
}