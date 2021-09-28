package com.example.loginpage.config;

import com.example.loginpage.Service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration //Bean을 사용하기 위한
@EnableWebSecurity
@AllArgsConstructor //모든 필드에 대한 생성자를 생성합니다
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private MemberService memberService;

    //암호화
    @Bean
    public PasswordEncoder passwordEncoder() { //PasswordEncoder password암호화 //스프링 시큐리티의 인터페이스 객체이다.
        return new BCryptPasswordEncoder(); //패스워드를 암호화하는 구연체
    }

    @Override//상위클래스에서 선언된 한 요소를 오버라이드 할 것
    public void configure(WebSecurity web) throws Exception{
        //인증을 무시하기 위한 설정
        web.ignoring().antMatchers("/css/**","/js/**","/ing/**","/lib/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .and()
                .formLogin()     // 로그인 설정
                .loginPage("/member/login")// 커스텀 login 페이지를 사용
                .defaultSuccessUrl("/")      // 로그인 성공 시 이동할 페이지
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)    // 세션 초기화
                .and()
                .exceptionHandling();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception { //AuthenticationManagerBuilder : Spring Security의 모든 인증을 관리한다.
        //로그인 처리를 하기위한 AuthenticationManagerBuiler를 설정
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }
}

