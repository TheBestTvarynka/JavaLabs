package kpi.java.exception;

public class UnsupportedActionException extends RuntimeException {
    public UnsupportedActionException() {
        super("Unsupported action!");
    }

    public UnsupportedActionException(String message) {
        super(message);
    }
}
