package kpi.java.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
        super("Wrong room number! Room not found.");
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}
