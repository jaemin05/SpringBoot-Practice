package com.example.practice.service.member;

import com.example.practice.payload.EmailRequest;
import com.example.practice.payload.EmailVerifiedRequest;
import com.example.practice.payload.MemberRequest;
import com.example.practice.payload.PasswordRequest;

public interface MemberService {
    void sendEmail(EmailRequest request);

    void verifyAccount(EmailVerifiedRequest request);

    void signup(MemberRequest request);

    void passwordChange(PasswordRequest request);
}
