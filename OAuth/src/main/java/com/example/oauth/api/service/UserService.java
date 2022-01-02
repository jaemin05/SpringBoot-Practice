package com.example.oauth.api.service;

import com.example.oauth.api.entity.user.User;
import com.example.oauth.api.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthRepository authRepository;

    public User getUser(String username){
        return authRepository.findByUsername(username);
    }
}
