package com.kpi.lab4.exception;

public class AlreadyBookedException extends RuntimeException{
    public AlreadyBookedException() {
        super("This room already booked! Please, choose another room.");
    }
    public AlreadyBookedException(String message) {
        super(message);
    }
}
