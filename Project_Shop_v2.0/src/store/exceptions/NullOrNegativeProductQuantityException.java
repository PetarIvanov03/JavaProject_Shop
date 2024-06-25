package store.exceptions;

public class NullOrNegativeProductQuantityException extends RuntimeException {
    public NullOrNegativeProductQuantityException(String message) {
        super(message);
    }
}
