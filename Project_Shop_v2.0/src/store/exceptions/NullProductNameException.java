package store.exceptions;

public class NullProductNameException extends RuntimeException {
    public NullProductNameException(String message) {
        super(message);
    }
}