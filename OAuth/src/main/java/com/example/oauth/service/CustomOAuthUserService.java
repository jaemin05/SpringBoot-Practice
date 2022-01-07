package com.example.oauth.service;

import com.example.oauth.domain.user.AuthProvider;
import com.example.oauth.domain.user.User;
import com.example.oauth.domain.user.UserRepository;
import com.example.oauth.exception.UserNotFound;
import com.example.oauth.security.oauth.user.OAuthUserInfo;
import com.example.oauth.security.oauth.user.OAuthUserInfoFactory;
import com.example.oauth.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomOAuthUserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(request);

        try{
            return processOAuth2User(request, oAuth2User);
        } catch (AuthenticationException e){
            throw e;
        } catch (Exception e){
            throw new InternalAuthenticationServiceException(e.getMessage(), e.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest request, OAuth2User user) {
        AuthProvider providerType = AuthProvider.valueOf(request.getClientRegistration().getRegistrationId().toUpperCase());

        OAuthUserInfo oAuth2UserInfo = OAuthUserInfoFactory.getOAuthUserInfo(providerType, user.getAttributes());
        User savedUser = userRepository.findByEmail(oAuth2UserInfo.getEmail())
                .orElseThrow(()->UserNotFound.Exception);

        if(savedUser != null){
            if(providerType != savedUser.getAuthProvider()) {
                throw new OAuth2AuthenticationException(
                        "Looks like you're signed up with " + providerType + "account. Please use your " + savedUser.getProviderId()
                                + " account to login."
                );
            }
            updateUser(savedUser, oAuth2UserInfo);
        } else{
                savedUser = createUser(request, oAuth2UserInfo);
            }
        return UserPrincipal.create(savedUser, user.getAttributes());
    }

    private User createUser(OAuth2UserRequest request, OAuthUserInfo userInfo) {
        LocalDateTime now = LocalDateTime.now();
        return userRepository.save(User.builder()
                .name(userInfo.getName())
                .email(userInfo.getEmail())
                .imageUrl(userInfo.getImageUrl())
                .authProvider(AuthProvider.valueOf(request.getClientRegistration().getRegistrationId()))
                .providerId(userInfo.getId())
                .createAt(now)
                .modifiedAt(now)
                .build()
        );
    }
    private User updateUser(User user, OAuthUserInfo userInfo){
        return userRepository.save(user
                .update(
                        userInfo.getName(),
                        userInfo.getImageUrl()
                )
        );
    }

}
