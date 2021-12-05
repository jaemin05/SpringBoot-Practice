package exception;

import error.ErrorCode;
import error.Exception.BusinessException;

public class CodeAlreadyExpiredException extends BusinessException {
    public CodeAlreadyExpiredException() {
        super(ErrorCode.CODE_ALREADY_EXPIRED);
    }
}
