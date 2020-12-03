package kpi.java.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException() {
        super("User already exist!");
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
