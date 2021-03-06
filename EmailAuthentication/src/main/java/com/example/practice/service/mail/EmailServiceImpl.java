package com.example.practice.service.mail;

import com.example.practice.Entity.certification.Certification;
import com.example.practice.Entity.certification.CertificationRepository;
import com.example.practice.Entity.certification.Certified;
import com.example.practice.exception.SendMessageFailedException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Value("${code.exp}")
    private Integer CODE_EXP;

    @Value("${mail.email}")
    private String myEmail;

    private final JavaMailSender javaMailSender;
    private final CertificationRepository certificationRepository;

    public String sendCode(String email) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            
            String code = getCode(createKey());
            message.setFrom(myEmail);
            message.addRecipients(Message.RecipientType.TO, email);
            message.setSubject("[이메일 인증]");
            message.setText(code);
            javaMailSender.send(message);
            return code;
        } catch (MessagingException e) {
            e.getStackTrace();
            throw new SendMessageFailedException();
        }
    }

    @Override
    @Transactional
    public void sendEmail(String email) {

        System.out.println("email com.example.practice.service");
        certificationRepository.findByEmail(email)
                .map(certification -> certificationRepository.save(certification.updateCode(sendCode(email))))
                .orElseGet(() -> certificationRepository.save(Certification.builder()
                        .code(sendCode(email))
                        .email(email)
                        .codeExp(CODE_EXP)
                        .certified(Certified.NOT_CERTIFIED)
                        .build())
                );
    }

    public String createKey() {
        return RandomStringUtils.randomNumeric(6);
    }

    public String getCode(String key) {
        return key.substring(0, 3) + "-" + key.substring(3, 6);
    }
}
