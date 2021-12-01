package service.mail;

import Entity.Certification;
import Entity.CertificationRepository;
import Entity.Certified;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender javaMailSender;
    private final CertificationRepository certificationRepository;

    @Transactional
    public String sendCode(String email) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try{
            String code = getCode(createKey());
            message.setFrom("test1@gmail.com");
            message.addRecipients(Message.RecipientType.TO, email);
            message.setSubject("[이메일 인증]");
            message.setText(code);
            javaMailSender.send(message);
            return code;
        } catch (MessagingException e) {
            e.getStackTrace();
            throw new 오류메시지;
        }
    }

    @Override
    @Transactional
    public void sendEmail(String email) {
        certificationRepository.findByEmail(email)
                .map(certification -> certificationRepository.save(certification.updateCode(sendCode(email))))
                .orElseGet(() -> certificationRepository.save(Certification.builder()
                        .code(sendCode(email))
                        .email(email)
                        .cerified(Certified.NOT_CERRIFED)
                        .build())
                );
    }

    public String createKey() {
        return RandomStringUtils.randomNumeric(6);
    }

    public String getCode(String key) {
        return key.substring(0,3) + "-" + key.substring(3,6);
    }
}
