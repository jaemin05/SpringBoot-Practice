package spring.emailauthentication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import spring.emailauthentication.enums.Gender;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@AllArgsConstructor
public class UserDto {
    private String email;
    private String password;
    private String phoneNumber;
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;


}
