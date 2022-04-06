package com.example.oauth.global.security.auth;

import com.example.oauth.domain.user.domain.repository.UserRepository;
import com.example.oauth.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new CustomUserDetails(
                userRepository.findByEmail(email)
                        .orElseThrow(() -> UserNotFoundException.Exception)
        );
    }
}
