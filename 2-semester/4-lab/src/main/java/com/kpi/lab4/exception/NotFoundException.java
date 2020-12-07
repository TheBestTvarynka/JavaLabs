package com.kpi.lab4.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Not found");
    }
    public NotFoundException(String message) {
        super(message);
    }
}
