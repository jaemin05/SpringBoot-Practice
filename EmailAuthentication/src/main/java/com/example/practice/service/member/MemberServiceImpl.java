package com.example.practice.service.member;

import com.example.practice.Entity.certification.Certification;
import com.example.practice.Entity.certification.CertificationRepository;
import com.example.practice.Entity.certification.Certified;
import com.example.practice.Entity.member.Member;
import com.example.practice.Entity.member.MemberRepository;
import com.example.practice.exception.*;
import com.example.practice.payload.EmailRequest;
import com.example.practice.payload.EmailVerifiedRequest;
import com.example.practice.payload.MemberRequest;
import com.example.practice.payload.PasswordRequest;
import com.example.practice.service.mail.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final CertificationRepository certificationRepository;
    private final MemberRepository memberRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void sendEmail(EmailRequest emailRequest) {
        if (certificationRepository.findByEmail(emailRequest.getEmail()).isPresent())
            throw new MemberEmailAlreadyExistsException();
        emailService.sendEmail(emailRequest.getEmail());
    }

    @Override
    public void verifyAccount(EmailVerifiedRequest request) {
        certificationRepository.findByEmail(request.getEmail())
                .filter(c -> request.getCode().equals(c.getCode()))
                .map(certification -> certificationRepository.save(certification.updateCertified(Certified.CERTIFIED)))
                .orElseThrow(() -> CodeNotCorrectException.EXCEPTION);
    }

    @Override
    @Transactional
    public void signup(MemberRequest request) {
        if (memberRepository.findByEmail(request.getEmail()).isPresent())
            throw new MemberNameAlreadyExistsException();

        Certification certification = certificationRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> CodeAlreadyExpiredException.EXCEPTION);

        if (certification.getCertified() == (Certified.CERTIFIED)) {
            memberRepository.save(Member.builder()
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build());
        } else throw new EmailNotCertifiedException();
    }

    @Transactional
    public void passwordChange(PasswordRequest request) {
        Member member = memberRepository.findByPassword(request.getPassword()).orElseThrow(PasswordNotFoundException::new);

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new PasswordNotCorrectException();
        }

        memberRepository.save(member.updatePassword(passwordEncoder.encode(request.getChange())));


    }
}
