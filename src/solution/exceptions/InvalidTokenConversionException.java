package solution.exceptions;

public class InvalidTokenConversionException extends RuntimeException {
    public InvalidTokenConversionException() {
    }

    public InvalidTokenConversionException(String message) {
        super(message);
    }

    public InvalidTokenConversionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTokenConversionException(Throwable cause) {
        super(cause);
    }
}
