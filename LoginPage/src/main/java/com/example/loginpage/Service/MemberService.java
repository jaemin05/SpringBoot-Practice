package com.example.loginpage.Service;

import com.example.loginpage.Controller.domain.Member;
import com.example.loginpage.Dto.MemberDto;
import com.example.loginpage.Repository.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberService implements UserDetailsService {
    private MemberRepository memberRepository;

    @Transactional
    public Long signUp(MemberDto memberDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        // password를 암호화하여 dp에 저장

        return memberRepository.save(memberDto.toEntity()).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //user정보를 조회하는 메서드
        Optional<Member> memberWrapper = memberRepository.findByusername(username);
        Member member = new Member();
        List<GrantedAuthority> authorities = new ArrayList<>();

        if ("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        //아이디 비미번호 권한 리스트를 매개변수로 User를 만들어 반환
        return new User(member.getUsername(),member.getPassword(), authorities);
    }


}
