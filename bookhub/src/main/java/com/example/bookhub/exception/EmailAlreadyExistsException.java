package com.example.bookhub.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String msg) {
        super(msg);
    }
}
