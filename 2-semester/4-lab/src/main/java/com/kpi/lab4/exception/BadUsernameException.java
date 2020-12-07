package com.kpi.lab4.exception;

public class BadUsernameException extends RuntimeException {
    public BadUsernameException() {
        super("Bad username!");
    }
    public BadUsernameException(String message) {
        super(message);
    }
}
