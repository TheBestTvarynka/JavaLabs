package kpi.java.exception;

public class BadUsernameException extends RuntimeException {
    public BadUsernameException() {
        super("Bad username!");
    }
    public BadUsernameException(String message) {
        super(message);
    }
}
