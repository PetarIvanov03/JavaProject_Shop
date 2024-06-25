package store.exceptions;

public class NullOrEmptyDateException extends RuntimeException {
    public NullOrEmptyDateException(String message) {
        super(message);
    }
}