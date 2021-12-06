package com.example.practice.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    MEMBER_NAME_ALREADY_EXISTS(409, "Member Name Already Exists"),
    MEMBER_EMAIL_ALREADY_EXISTS(409, "Member Email Already Exists"),
    MEMBER_NOT_FOUND(500, "Member Not Found"),

    EMAIL_NOT_CERTIFIED(401,"Email Not certified"),
    SEND_MESSAGE_FAILED(500, "Send Message Failed"),

    CODE_NOT_CORRECT(401, "Code Not Correct"),
    CODE_ALREADY_EXPIRED(401, "Code Already Expired"),

    PASSWORD_NOT_CORRECT(401, "Password Not Correct"),
    PASSWORD_NOT_FOUND(500, "Password Not Found");


    private int statues;
    private String message;
}
