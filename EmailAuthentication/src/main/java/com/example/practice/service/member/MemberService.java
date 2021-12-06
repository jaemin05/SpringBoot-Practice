package com.example.practice.service.member;

import com.example.practice.payload.EmailRequest;
import com.example.practice.payload.EmailVerifiedRequest;
import com.example.practice.payload.MemberRequest;

public interface MemberService {
    void sendEmail(EmailRequest request);
    void verifyAccount(EmailVerifiedRequest request);
    void signup(MemberRequest request);
}
