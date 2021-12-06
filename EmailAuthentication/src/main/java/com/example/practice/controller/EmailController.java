package com.example.practice.controller;

import com.example.practice.payload.PasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.practice.payload.EmailRequest;
import com.example.practice.payload.EmailVerifiedRequest;
import com.example.practice.payload.MemberRequest;
import com.example.practice.service.member.MemberService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class EmailController {
    private final MemberService memberService;

    @PostMapping("/send")
    public void sendEmail(@Valid @RequestBody EmailRequest request){
        memberService.sendEmail(request);
    }

    @PostMapping("/verify")
    public void verifyAccount(@Valid @RequestBody EmailVerifiedRequest request) {
        memberService.verifyAccount(request);
    }

    @PostMapping("/signup")
    public void signup(@Valid @RequestBody MemberRequest request) {
        memberService.signup(request);
    }

    @PostMapping("/change")
    public void change(@Valid @RequestBody PasswordRequest request) {
        memberService.passwordChange(request);
    }

}
