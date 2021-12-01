package service.member;

import payload.EmailRequest;
import payload.EmailVerifiedRequest;

public interface MemberService {
    void sendEmail(EmailRequest request);
    void verifyAccount(EmailVerifiedRequest request);
}
