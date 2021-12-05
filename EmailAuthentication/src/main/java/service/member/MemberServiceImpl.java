package service.member;

import Entity.certification.Certification;
import Entity.certification.CertificationRepository;
import Entity.certification.Certified;
import Entity.member.Member;
import Entity.member.MemberRepository;
import exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import payload.EmailRequest;
import payload.EmailVerifiedRequest;
import payload.MemberRequest;
import service.mail.EmailService;

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
        if(certificationRepository.findByEmail(emailRequest.getEmail()).isPresent())
            throw new MemberEmailAlreadyExistsException();
        emailService.sendEmail(emailRequest.getEmail());
    }

    @Override
    public void verifyAccount(EmailVerifiedRequest request) {
        certificationRepository.findByEmail(request.getEmail())
                .filter(c -> request.getCode().equals(c.getCode()))
                .map(certification -> certificationRepository.save(certification.updateCertified(Certified.CERTIFIED)))
                .orElseThrow(CodeNotCorrectException::new);
    }

    @Override
    @Transactional
    public void signup(MemberRequest request) {
        if(memberRepository.findByEmail(request.getEmail()).isPresent())
            throw new MemberNameAlreadyExistsException();

        Certification certification = certificationRepository.findByEmail(request.getEmail())
                .orElseThrow(CodeAlreadyExpiredException::new);

        if(certification.getCertified() == (Certified.CERTIFIED)) {
            memberRepository.save(Member.builder()
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build());
        } else throw new EmailNotCertifiedException();
    }


}
