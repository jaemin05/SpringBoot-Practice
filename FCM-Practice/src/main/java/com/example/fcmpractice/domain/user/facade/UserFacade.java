package com.example.fcmpractice.domain.user.facade;

import com.example.fcmpractice.domain.user.domain.User;
import com.example.fcmpractice.domain.user.domain.repository.UserRepository;
import com.example.fcmpractice.domain.user.exception.AccountIdAlreadyExistsException;
import com.example.fcmpractice.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    public User getCurrentUser() {
         String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
         return getByAccountId(accountId);
    }

    public User getByAccountId(String accountId) {
        return userRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public void accountIdAlreadyExists(String accountId) {
        if(userRepository.findByAccountId(accountId).isPresent())
            throw AccountIdAlreadyExistsException.EXCEPTION;
    }
}
