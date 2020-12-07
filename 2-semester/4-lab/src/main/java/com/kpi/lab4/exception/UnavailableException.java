package com.kpi.lab4.exception;

public class UnavailableException extends RuntimeException {
    public UnavailableException() {
        super("Sorry, we are temporary unavailable. Please, try later.");
    }
    public UnavailableException(String message) {
        super(message);
    }
}
