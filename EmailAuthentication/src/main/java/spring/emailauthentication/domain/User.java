package spring.emailauthentication.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import spring.emailauthentication.dto.UserDto;
import spring.emailauthentication.enums.Gender;

import javax.persistence.*;
import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@Entity
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String phoneNumber;
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    //private List<Thumbnail> thumbnailList = new ArrayList<>();

    @Builder
    public User(String email, String password, String phoneNumber, String name, Gender gender){
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.gender = gender;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User updateInfo(UserDto userDto) {
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.password = password;
        return this;
    }
}
