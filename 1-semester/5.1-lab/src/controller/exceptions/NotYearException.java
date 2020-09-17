package controller.exceptions;;

public class NotYearException extends RuntimeException {
    NotYearException() {
        super("Entered value is not valid for year!");
    }
    public NotYearException(String message) {
        super(message);
    }
}
