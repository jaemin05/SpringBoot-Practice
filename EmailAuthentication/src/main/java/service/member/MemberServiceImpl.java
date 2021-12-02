package service.member;

import Entity.certification.CertificationRepository;
import Entity.certification.Certified;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import payload.EmailRequest;
import payload.EmailVerifiedRequest;
import service.mail.EmailService;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final CertificationRepository certificationRepository;
    private final EmailService emailService;

    @Override
    @Transactional
    public void sendEmail(EmailRequest emailRequest) {
        if(certificationRepository.findByEmail(emailRequest.getEmail()).isPresent())
            throw new 에러메시지;
        emailService.sendEmail(emailRequest.getEmail());
    }

    @Override
    public void verifyAccount(EmailVerifiedRequest request) {
        certificationRepository.findByEmail(request.getEmail())
                .filter(c -> request.getCode().equals(c.getCode()))
                .map(certification -> certificationRepository.save(certification.updateCretified(Certified.CERTIFIED)))
                .orElseThrow(에러메시지);
    }


}
