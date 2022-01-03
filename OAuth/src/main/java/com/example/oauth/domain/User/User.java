package com.example.oauth.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String imageUrl;

    @Column
    private Role role;

    @Column(nullable = false)
    private boolean emailVerified = false;

    @Column
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    @Column
    private String providerId;

    public User(String name, String email, String imageUrl, Role role,
                boolean emailVerified, String password, AuthProvider authProvider, String providerId){
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.role = role;
        this.emailVerified = emailVerified;
        this.password = password;
        this.authProvider = authProvider;
        this.providerId = providerId;
    }

    public User update(String name, String imageUrl){
        this.name = name;
        this.imageUrl = imageUrl;
        return this;
    }
}
