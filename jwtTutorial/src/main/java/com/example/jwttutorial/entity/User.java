package com.example.jwttutorial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user ")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @JsonIgnore //필드 레벨에서 무시 될 수있는 속성을 표시하는 데 사용됩니다.
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 50, unique = true) //unigue 중복X
    private String username;

    @JsonIgnore
    @Column(name = "password", length = 100)
    private String password;

    @Column(name="nickname", length = 50)
    private String nickname;

    @Column(name = "activated")
    private Boolean activated;

    @ManyToMany
    @JoinTable(
            name="user_authority",
            joinColumns = {@JoinColumn(name="user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name="authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities; //Set 중복X


    public boolean isActivated() {
        return activated;
    }
}
