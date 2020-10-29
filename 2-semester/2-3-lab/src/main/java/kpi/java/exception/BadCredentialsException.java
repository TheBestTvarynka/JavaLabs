package kpi.java.exception;

public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException() {
        super("Incorrect username or password!");
    }
    public BadCredentialsException(String message) {
        super(message);
    }
}
