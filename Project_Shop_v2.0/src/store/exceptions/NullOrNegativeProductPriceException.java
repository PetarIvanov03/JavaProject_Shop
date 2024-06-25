package store.exceptions;

public class NullOrNegativeProductPriceException extends RuntimeException {
    public NullOrNegativeProductPriceException(String message) {
        super(message);
    }
}
