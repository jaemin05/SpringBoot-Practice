package com.example.oauth.service;

import com.example.oauth.config.AppProperties;
import com.example.oauth.domain.user.*;
import com.example.oauth.dto.Request.LoginRequest;
import com.example.oauth.dto.Request.SignUpRequest;
import com.example.oauth.dto.Request.TokenRequest;
import com.example.oauth.exception.*;
import com.example.oauth.security.UserPrincipal;
import com.example.oauth.security.token.AuthToken;
import com.example.oauth.security.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AppProperties appProperties;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final UserRefreshTokenRepository userRefreshTokenRepository;

    @Transactional
    public ResponseEntity<?> signup(SignUpRequest signUpRequest){
        if(userRepository.existsByEmail(signUpRequest.getEmail())){
            throw EmailAlreadyExistsException.Exception;
        }

        User result = userRepository.save(User.builder()
                .name(signUpRequest.getName())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .authProvider(AuthProvider.LOCAL)
                .build());

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).body("성공적으로 계졍이 생성되었습니다.");
    }

    @Transactional
    public TokenRequest login(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Date now = new Date();
        String userId = loginRequest.getEmail();
        AuthToken accessToken = tokenProvider.createToken(
            userId,
                ((UserPrincipal)authentication.getPrincipal()).getRole().getKey(),
                new Date(now.getTime() + appProperties.getAuth().getTokenExpiry())
        );

        long refreshTokenExpiry = appProperties.getAuth().getRefreshTokenExpiry();
        AuthToken refreshToken = tokenProvider.createToken(
                appProperties.getAuth().getTokenSecret(),
                new Date(now.getTime() + refreshTokenExpiry)
        );

        UserRefreshToken userRefreshToken = userRefreshTokenRepository.findByUserId(userId)
                .orElseThrow(() -> RefreshTokenNotFoundException.Exception);
        if(refreshToken == null){
            userRefreshToken = new UserRefreshToken(userId, refreshToken.getToken());
            userRefreshTokenRepository.save(userRefreshToken);
        } else {
            userRefreshToken.setRefreshToken(refreshToken.getToken());
        }
        return new TokenRequest(accessToken.getToken(), refreshToken.getToken());
    };

    @Transactional
    public String reissue(TokenRequest tokenRequest){
        AuthToken authToken = tokenProvider.convertAuthToken(tokenRequest.getRefreshToken());
        if(!authToken.validate()){
            throw InvalidRefreshTokenException.Exception;
        }

        AuthToken accessToken = tokenProvider.convertAuthToken(tokenRequest.getAccessToken());
        if(!accessToken.validate()){
            throw InvalidAccessTokenException.Exception;
        }

        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        UserRefreshToken refreshToken = userRefreshTokenRepository
                .findByUserIdAndRefreshToken(authentication.getName(), tokenRequest.getRefreshToken())
                .orElseThrow(() -> UserLogoutException.Exception);

        if(!refreshToken.getRefreshToken().equals(tokenRequest.getRefreshToken())){
            throw UserNotFoundException.Exception;
        }

        Date now = new Date();
        long refreshTokenExpiry = appProperties.getAuth().getRefreshTokenExpiry();

         AuthToken tokenResponse = tokenProvider.createToken(
                appProperties.getAuth().getTokenSecret(),
                new Date(now.getTime() + refreshTokenExpiry)
        );

        UserRefreshToken newRefreshToken = refreshToken.updateValue(tokenResponse.getToken());
        userRefreshTokenRepository.save(newRefreshToken);


        return newRefreshToken.getRefreshToken();
    }
}
