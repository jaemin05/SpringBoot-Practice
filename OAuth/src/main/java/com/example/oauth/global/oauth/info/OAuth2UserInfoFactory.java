package com.example.oauth.global.oauth.info;

import com.example.oauth.domain.user.domain.ProviderType;
import com.example.oauth.global.oauth.exception.InvalidProviderTypeException;
import com.example.oauth.global.oauth.info.impl.FacebookOAuth2UserInfo;
import com.example.oauth.global.oauth.info.impl.GoogleOAuth2UserInfo;
import com.example.oauth.global.oauth.info.impl.KakaoOAuth2UserInfo;
import com.example.oauth.global.oauth.info.impl.NaverOAuth2UserInfo;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(ProviderType providerType, Map<String, Object> attributes) {

        switch (providerType) {
            case GOOGLE:
                return GoogleOAuth2UserInfo.ofGoogle(attributes);
            case FACEBOOK:
                return FacebookOAuth2UserInfo.ofFacebook(attributes);
            case NAVER:
                return NaverOAuth2UserInfo.ofNaver(attributes);
            case KAKAO:
                return KakaoOAuth2UserInfo.ofKakao(attributes);
            default:
                throw InvalidProviderTypeException.Exception;
        }
    }
}
