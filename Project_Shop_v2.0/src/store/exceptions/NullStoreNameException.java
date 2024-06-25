package store.exceptions;

public class NullStoreNameException extends RuntimeException {
    public NullStoreNameException(String message) {
        super(message);
    }
}