package com.kpi.javatrack.controller.exceptions;

public class NotAmountException extends RuntimeException {
    NotAmountException() {
        super("Entered amount is not valid!");
    }
    public NotAmountException(String message) {
        super(message);
    }
}
