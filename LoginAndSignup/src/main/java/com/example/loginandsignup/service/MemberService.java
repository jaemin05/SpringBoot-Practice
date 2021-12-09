package com.example.loginandsignup.service;

import com.example.loginandsignup.Repository.MemberRepository;
import com.example.loginandsignup.domain.Member;
import com.example.loginandsignup.dto.MemberDto;
import com.example.loginandsignup.dto.TokenDto;
import com.example.loginandsignup.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Transactional
    public String signUp(MemberDto memberDto) {
        memberRepository.save(Member.builder()
                .username(memberDto.getUsername())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .build());

        return "Success Signup";
    }

    public TokenDto login(MemberDto memberDto) {
        Member member = memberRepository.findByUsername(memberDto.getUsername()).get();

        if (!passwordEncoder.matches(memberDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("Login Failed");
        }

        return new TokenDto(tokenProvider.createToken(member.getUsername()));
    }

}
