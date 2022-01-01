package com.example.oauth.api.entity.user;

import com.example.oauth.oauth.entity.ProviderType;
import com.example.oauth.oauth.entity.RoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User {
    //JsonIgnore: 필드 레벨에서 무시 될 수있는 속성을 표시하는 데 사용됩니다.
    //@JsonIgnore
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "USERNAME", length = 100)
    @NotNull
    @Size(max = 100)
    private String username;

    @Column(name = "PASSWORD", length = 128)
    @NotNull
    @Size(max = 128)
    private String password;

    @Column(name = "EMAIL", length = 512, unique = true)
    @NotNull
    @Size(max = 512)
    private String email;

    @Column(name = "EMAIL_VERIFIED")
    @NotNull
    private boolean emailVerified;

    @Column(name = "PROFILE_IMAGE_URL", length = 512)
    @NotNull
    @Size(max = 512)
    private String profileImageUrl;

    @Column(name = "PROVIDER_TYPE",length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private ProviderType providerType;

    @Column(name = "ROLE_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleType roleType;

    @Column(name = "CREATE_AT")
    @NotNull
    private LocalDateTime createAt;

    @Column(name = "MODIFIED_AT")
    @NotNull
    private LocalDateTime modifiedAt;

    public User(
            @NotNull @Size(max = 100) String username,
            @NotNull @Size(max = 128) String password,
            @NotNull @Size(max = 512) String email,
            @NotNull boolean emailVerified,
            @NotNull @Size(max = 512) String profileImageUrl,
            @NotNull ProviderType providerType,
            @NotNull RoleType roleType,
            @NotNull LocalDateTime createAt,
            @NotNull LocalDateTime modifiedAt
            ) {
        this.username = username;
        this.password = password != null ? password : "NO_PASSWORD";
        this.email = email != null ? email : "NO_EMAIL";
        this.emailVerified = emailVerified;
        this.profileImageUrl = profileImageUrl != null ? profileImageUrl : "";
        this.providerType = providerType;
        this.roleType = roleType;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }
}
