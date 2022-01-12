package com.example.oauth.service;

import com.example.oauth.domain.user.User;
import com.example.oauth.domain.user.UserRepository;
import com.example.oauth.exception.UserNotFoundException;
import com.example.oauth.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> UserNotFoundException.Exception);
        if(user == null){
            throw UserNotFoundException.Exception;
        }
        return UserPrincipal.create(user);
    }

}
