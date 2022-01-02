package com.example.oauth.api.repository;

import com.example.oauth.api.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AuthRepository extends JpaRepository<User,Long > {
    User findByUsername(String username);
}
