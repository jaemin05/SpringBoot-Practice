package exception;

import error.ErrorCode;
import error.Exception.BusinessException;

public class SendMessageFailedException extends BusinessException {
    public SendMessageFailedException() {
        super(ErrorCode.SEND_MESSAGE_FAILED);
    }
}
