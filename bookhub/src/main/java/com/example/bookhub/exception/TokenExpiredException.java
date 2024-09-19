package com.example.bookhub.exception;

public class TokenExpiredException extends RuntimeException {

    public TokenExpiredException(String msg) {
        super(msg);
    }
}
