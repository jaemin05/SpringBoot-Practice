package spring.LoginLogout.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.LoginLogout.domain.User;
import spring.LoginLogout.dto.UserDto;
import spring.LoginLogout.repository.UserRepository;
import spring.LoginLogout.security.JwtProvider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public String join(UserDto user) {
        userRepository.save(User.builder()
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .gender(user.getGender())
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
        return "Succssful join";
    }

    public UserDto login(UserDto userDto, HttpServletResponse response) {
        User member = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));

        if (!passwordEncoder.matches(userDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        String token = jwtProvider.createToken(member.getUsername(), member.getRoles());


        Cookie cookie = new Cookie("X-AUTH-TOKEN", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true); //서버 요청이 있을 떄에만
        cookie.setSecure(true); // SSL 통신채널 연결 시에만 쿠키를 전송하도록 설정
        response.addCookie(cookie);

        return new UserDto(member);
    }
}
