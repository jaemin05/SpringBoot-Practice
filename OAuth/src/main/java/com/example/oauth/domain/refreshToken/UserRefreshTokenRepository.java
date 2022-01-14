package com.example.oauth.domain.refreshToken;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, String> {
    Optional<UserRefreshToken> findByRefreshToken(String refreshToken);
}
