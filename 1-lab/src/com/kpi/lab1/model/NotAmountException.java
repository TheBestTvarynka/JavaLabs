package com.kpi.lab1.model;

public class NotAmountException extends RuntimeException {
    NotAmountException() {
        super("Entered amount is not valid!");
    }
    NotAmountException(String message) {
        super(message);
    }
}
