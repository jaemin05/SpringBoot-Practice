package com.example.oauth.security.oauth.handler;

import com.example.oauth.security.repository.HttpCookieOAuthAuthorizationRequestRepository;
import com.example.oauth.utils.CookieUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.example.oauth.security.repository.HttpCookieOAuthAuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

@Component
@RequiredArgsConstructor
public class OAuthAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private final HttpCookieOAuthAuthorizationRequestRepository httpCookieOAuthAuthorizationRequestRepository;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException, ServletException {
        String targetUrl = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue)
                .orElse(("/"));
        //printStackTrace: 에러메시지의 발생 근원지를 찾아 단계별로 에러 출력
        e.printStackTrace();

        //UriComponentsBuilder: 여러가지 파라미터들을 연결시켜 하나의 URL 링크로 만들어서 반환해준다.
        targetUrl = UriComponentsBuilder.fromHttpUrl(targetUrl)
                .queryParam("error", e.getLocalizedMessage())
                .build().toUriString();

        httpCookieOAuthAuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

}
