package com.kpi.lab1.model;

public class NotYearException extends Exception {
    NotYearException() {
        super("Entered value is not valid for year!");
    }
    NotYearException(String message) {
        super(message);
    }
}
