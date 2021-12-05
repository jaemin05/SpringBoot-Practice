package exception;

import error.ErrorCode;
import error.Exception.BusinessException;

public class EmailNotCertifiedException extends BusinessException {
    public EmailNotCertifiedException(){
        super(ErrorCode.EMAIL_NOT_CERTIFIED);
    }
}
