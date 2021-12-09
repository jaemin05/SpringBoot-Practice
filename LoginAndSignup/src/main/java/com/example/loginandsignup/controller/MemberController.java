package com.example.loginandsignup.controller;

import com.example.loginandsignup.dto.MemberDto;
import com.example.loginandsignup.dto.TokenDto;
import com.example.loginandsignup.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/home")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/member/signup")
    public String signup(@RequestBody MemberDto memberDto) {
        return memberService.signUp(memberDto);
    }

    @PostMapping("/member/login")
    public TokenDto login(@RequestBody MemberDto memberDto) {
        return memberService.login(memberDto);
    }
}
