package spring.LoginLogout.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.LoginLogout.domain.User;
import spring.LoginLogout.enums.Gender;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String email;
    private String password;
    private String phoneNumber;
    private String name;
    private Gender gender;


    public UserDto(User member) {
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.phoneNumber = member.getPhoneNumber();
        this.name = member.getName();
        this.gender = member.getGender();
    }
}
