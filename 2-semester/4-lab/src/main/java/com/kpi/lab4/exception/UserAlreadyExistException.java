package com.kpi.lab4.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException() {
        super("User already exist!");
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
