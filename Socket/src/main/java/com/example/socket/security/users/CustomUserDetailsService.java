package com.example.socket.security.users;

import com.example.socket.exception.MemberNotFoundException;
import com.example.socket.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    
    @Override
    public CustomUserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return memberRepository.findById(id)
                .map(CustomUserDetails::new).orElseThrow(() -> MemberNotFoundException.Exception);
    }
}
