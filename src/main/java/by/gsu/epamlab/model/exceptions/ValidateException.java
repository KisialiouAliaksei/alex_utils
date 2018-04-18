package by.gsu.epamlab.model.exceptions;

/**
 * Created by alex on 25.06.2016.
 */
public class ValidateException extends Exception {
    public ValidateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidateException(Throwable cause) {
        super(cause);
    }

    public ValidateException(String message) {
        super(message);
    }

    public ValidateException() {
    }
}
