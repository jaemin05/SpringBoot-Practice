package com.example.oauth.api.repository;

import com.example.oauth.api.entity.user.UserRefreshToken;
import org.aspectj.weaver.loadtime.Options;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Long> {
    Optional<UserRefreshToken> findByRefreshToken(String refreshToken);
}
