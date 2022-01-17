package com.example.oauth.service.user;

import com.example.oauth.domain.user.User;
import com.example.oauth.domain.user.UserRepository;
import com.example.oauth.dto.response.UserResponse;
import com.example.oauth.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse getUser(){
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userId = principal.getUsername();

        User user = userRepository.findByEmail(userId)
                .orElseThrow(() -> UserNotFoundException.Exception);


        return new UserResponse(user);
    }
}
