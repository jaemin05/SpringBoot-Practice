package com.example.oauth.security.oauth.handler;

import com.example.oauth.config.AppProperties;
import com.example.oauth.domain.user.AuthProvider;
import com.example.oauth.domain.user.Role;
import com.example.oauth.domain.user.UserRefreshToken;
import com.example.oauth.domain.user.UserRefreshTokenRepository;
import com.example.oauth.exception.RefreshTokenNotFoundException;
import com.example.oauth.exception.UriNotAuthorizedException;
import com.example.oauth.security.oauth.user.OAuthUserInfo;
import com.example.oauth.security.oauth.user.OAuthUserInfoFactory;
import com.example.oauth.security.repository.HttpCookieOAuthAuthorizationRequestRepository;
import com.example.oauth.security.token.AuthToken;
import com.example.oauth.security.token.TokenProvider;
import com.example.oauth.utils.CookieUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import static com.example.oauth.security.repository.HttpCookieOAuthAuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;
import static com.example.oauth.security.repository.HttpCookieOAuthAuthorizationRequestRepository.REFRESH_TOKEN;

@Component
@RequiredArgsConstructor
public class OAuthAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private TokenProvider tokenProvider;
    private AppProperties appProperties;
    private final UserRefreshTokenRepository userRefreshTokenRepository;
    private final HttpCookieOAuthAuthorizationRequestRepository httpCookieOAuthAuthorizationRequestRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException{
        String targetUrl = determineTargetUrl(request, response, authentication);
        if(response.isCommitted()){
            logger.debug("응답이 이미 커밋되었습니다. " + targetUrl + "로 리다이렉션을 할 수 없습니다.");
            return;
        }
        clearAuthenticationAttributes(request,response);
        getRedirectStrategy().sendRedirect(request,response,targetUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
        Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie :: getValue);

        if(redirectUri.isPresent() && isAuthorizedRedirectUri(redirectUri.get())){
            throw UriNotAuthorizedException.Exception;
        }

        String targetUri = redirectUri.orElse(getDefaultTargetUrl());

        OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
        AuthProvider authProvider = AuthProvider.valueOf(authToken.getAuthorizedClientRegistrationId().toUpperCase());

        //OpenIDConnect
        OidcUser user = ((OidcUser) authentication.getPrincipal());
        OAuthUserInfo userInfo = OAuthUserInfoFactory.getOAuthUserInfo(authProvider, user.getAttributes());
        Collection<? extends GrantedAuthority> authorities = ((OidcUser) authentication.getPrincipal()).getAuthorities();

        Role role = hasAuthority(authorities, Role.ADMIN.getKey()) ? Role.ADMIN : Role.USER;

        Date now = new Date();
        AuthToken accessToken = tokenProvider.createToken(
                userInfo.getId(),
                role.getKey(),
                new Date(now.getTime() + appProperties.getAuth().getTokenExpiry())
        );

        long refreshTokenExpiry = appProperties.getAuth().getRefreshTokenExpiry();

        AuthToken refreshToken = tokenProvider.createToken(
                appProperties.getAuth().getTokenSecret(),
                new Date(now.getTime() + refreshTokenExpiry)
        );

        UserRefreshToken userRefreshToken = userRefreshTokenRepository.findByUserId(userInfo.getId())
                .orElseThrow(() -> RefreshTokenNotFoundException.Exception);

        if(userRefreshToken != null){
            userRefreshToken.setRefreshToken(refreshToken.getToken());
        } else {
            userRefreshToken = new UserRefreshToken(userInfo.getId(), refreshToken.getToken());
            userRefreshTokenRepository.save(userRefreshToken);
        }

        int cookieMaxAge = (int) refreshTokenExpiry/60;

        CookieUtils.deleteCookie(request, response, REFRESH_TOKEN);
        CookieUtils.addCookie(response, REFRESH_TOKEN, refreshToken.getToken(), cookieMaxAge);

        return UriComponentsBuilder.fromUriString(targetUri)
                .queryParam("token", accessToken.getToken())
                .build().toUriString();
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response){
        super.clearAuthenticationAttributes(request);
        httpCookieOAuthAuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    private boolean hasAuthority(Collection<? extends GrantedAuthority> authorities, String authority){
        if(authorities == null){
            return false;
        }
        for(GrantedAuthority grantedAuthority : authorities) {
            if (authority.equals(grantedAuthority.getAuthority())) {
                return true;
            }
        }
        return false;
    }

    private boolean isAuthorizedRedirectUri(String uri){
        URI clientRedirectUri = URI.create(uri);

        return appProperties.getOAuth2().getAuthorizedRedirectUris()
                .stream()
                .anyMatch(authorizedRedirectUri ->{
                    //호스트와 포트만 확인하십시오. 클라이언트가 원하는 경우 다른 경로를 사용하도록 허용
                    URI authorizedURI = URI.create(authorizedRedirectUri);
                    if(authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
                        && authorizedURI.getPort() == clientRedirectUri.getPort()){
                        return true;
                    }
                    return false;
                });
    }
}

