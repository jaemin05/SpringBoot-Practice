package com.example.oauth.config;

import com.example.oauth.domain.user.Role;
import com.example.oauth.security.token.TokenAuthenticationFilter;
import com.example.oauth.security.token.TokenProvider;
import com.example.oauth.service.CustomOAuth2UserService;
import com.example.oauth.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomOAuth2UserService customOAuthUserService;
    private final TokenProvider tokenProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/api/**").hasAnyAuthority(Role.USER.getKey())
                .antMatchers("/api/**/admin/**").hasAnyAuthority(Role.ADMIN.getKey())
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .authorizationEndpoint()
                //클라이언트 처음 로그인 URI
                .baseUri("/oauth2/authorization")
                .and()
                .redirectionEndpoint()
                .baseUri("/*/oauth2/code/*")
                .and()
                .userInfoEndpoint()
                .userService(customOAuthUserService);
    }

    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenProvider);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
