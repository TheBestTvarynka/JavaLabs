package kpi.java.exception;

public class BadEmailException extends RuntimeException {
    public BadEmailException() {
        super("Bad username!");
    }

    public BadEmailException(String message) {
        super(message);
    }
}
