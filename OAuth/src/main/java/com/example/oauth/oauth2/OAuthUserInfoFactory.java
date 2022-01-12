package com.example.oauth.oauth2;

import com.example.oauth.domain.user.AuthProvider;
import com.example.oauth.exception.ProviderTypeInvalidException;
import com.example.oauth.oauth2.impl.NaverOAuthUserInfo;
import com.example.oauth.oauth2.impl.FaceBookOAuthUserInfo;
import com.example.oauth.oauth2.impl.GoogleOAuthUserInfo;
import com.example.oauth.oauth2.impl.KakaoOAuthUserInfo;

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
