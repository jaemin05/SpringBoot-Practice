package exception;

import error.ErrorCode;
import error.Exception.BusinessException;
public class MemberEmailAlreadyExistsException extends BusinessException {
    public MemberEmailAlreadyExistsException() {
        super(ErrorCode.MEMBER_EMAIL_ALREADY_EXISTS);
    }
}
