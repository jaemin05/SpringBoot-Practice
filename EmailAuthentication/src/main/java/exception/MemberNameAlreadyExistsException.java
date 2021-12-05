package exception;

import error.ErrorCode;
import error.Exception.BusinessException;

public class MemberNameAlreadyExistsException extends BusinessException {
    public MemberNameAlreadyExistsException(){
        super(ErrorCode.MEMBER_NAME_ALREADY_EXISTS);
    }
}
