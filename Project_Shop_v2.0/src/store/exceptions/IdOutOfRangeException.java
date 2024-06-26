package store.exceptions;

public class IdOutOfRangeException extends RuntimeException {
    public IdOutOfRangeException(String message) {
        super(message);
    }
}