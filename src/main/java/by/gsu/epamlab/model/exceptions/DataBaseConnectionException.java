package by.gsu.epamlab.model.exceptions;

/**
 * Created by alex on 26.06.2016.
 */
public class DataBaseConnectionException extends Exception {
    public DataBaseConnectionException() {
    }

    public DataBaseConnectionException(String message) {
        super(message);
    }

    public DataBaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataBaseConnectionException(Throwable cause) {
        super(cause);
    }
}
