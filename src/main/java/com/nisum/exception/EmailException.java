package com.nisum.exception;

public class EmailException extends Exception{

    public static final String INVALID_EMAIL = "Invalid email";
    public static final String EXISTS_EMAIL = "The email is already registered";

    public EmailException(String message) {
        super(message);
    }
}
