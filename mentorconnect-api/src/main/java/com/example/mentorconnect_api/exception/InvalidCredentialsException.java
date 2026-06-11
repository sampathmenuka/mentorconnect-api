package com.example.mentorconnect_api.exception;

public class InvalidCredentialsException
        extends RuntimeException {

    public InvalidCredentialsException(String message) {
        super(message);
    }
}