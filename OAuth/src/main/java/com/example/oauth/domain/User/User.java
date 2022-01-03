package com.example.oauth.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_NAME", length = 100)
    @NotNull
    @Size(max = 100)
    private String name;

    @Column(name = "USER_EMAIL", length = 512, unique = true)
    @NotNull
    @Size(max = 512)
    private String email;

    @Column(name = "USER_IMAGE_URL", length = 512)
    @NotNull
    @Size(max = 512)
    private String imageUrl;

    @Column(name = "ROLE_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

    @Column(name = "EMAIL_VERIFIED")
    @NotNull
    private Boolean emailVerified = false;

    @Column(name = "USER_PWD", length = 128)
    @NotNull
    @Size(max = 128)
    private String password;

    @Column(name = "PROVIDER_TYPE", length = 20)
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    @Column(name = "PROVIDER_ID", length = 64)
    @NotNull
    @Size(max = 64)
    private String providerId;

    @Column(name = "CREATE_AT")
    @NotNull
    private LocalDateTime createAt;

    @Column(name = "MODIFIED_AT")
    @NotNull
    private LocalDateTime modifiedAt;

    public User(
            String name,
            String email,
            String imageUrl,
            Role role,
            Boolean emailVerified,
            String password,
            AuthProvider authProvider,
            String providerId,
            LocalDateTime createAt,
            LocalDateTime modifiedAt
    ){
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.role = role;
        this.emailVerified = emailVerified;
        this.password = password;
        this.authProvider = authProvider;
        this.password = password;
        this.authProvider = authProvider;
        this.providerId = providerId;
        this. createAt = createAt;
        this. modifiedAt = modifiedAt;
    }
    public User update(String name, String imageUrl){
        this.name = name;
        this.imageUrl = imageUrl;
        return this;
    }
}
