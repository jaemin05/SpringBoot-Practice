package com.example.practice.controller;

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
        System.out.println("controller");
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

}
