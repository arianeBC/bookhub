package com.example.bookhub.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum BusinessErrorCodes {

    NO_CODE(0, NOT_IMPLEMENTED, "No code."),
    INCORRECT_PASSWORD(300, BAD_REQUEST, "Current password is incorrect."),
    NEW_PASSWORD_DOES_NOT_MATCH(301, BAD_REQUEST, "The new password does not match."),
    ACCOUNT_LOCKED(302, FORBIDDEN, "User account is locked."),
    ACCOUNT_DISABLED(303, FORBIDDEN, "User account is disabled."),
    BAD_CREDENTIALS(304, FORBIDDEN, "Login and / or password is incorrect."),
    ;

    private final int code;
    private final String message;
    private final HttpStatus httpsStatus;

    BusinessErrorCodes(int code, HttpStatus httpsStatus, String message) {
        this.code = code;
        this.httpsStatus = httpsStatus;
        this.message = message;
    }
}
