package error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    MEMBER_NAME_ALREADY_EXISTS(409, "Member Name Already Exists"),
    MEMBER_EMAIL_ALREADY_EXISTS(409, "Member Email Already Exists"),

    EMAIL_NOT_CERTIFIED(401,"Email Not certified"),
    SEND_MESSAGE_FAILED(500, "Send Message Failed"),

    CODE_NOT_CORRECT(401, "Code Not Correct"),
    CODE_ALREADY_EXPIRED(401, "Code Already Expired");

    private int statues;
    private String message;
}
