package exception;

import error.ErrorCode;
import error.Exception.BusinessException;

public class CodeNotCorrectException extends BusinessException {
    public CodeNotCorrectException() {
        super(ErrorCode.CODE_NOT_CORRECT);
    }
}
