package service.member;

import payload.EmailRequest;
import payload.EmailVerifiedRequest;
import payload.MemberRequest;

public interface MemberService {
    void sendEmail(EmailRequest request);
    void verifyAccount(EmailVerifiedRequest request);
    void signup(MemberRequest request);
}
