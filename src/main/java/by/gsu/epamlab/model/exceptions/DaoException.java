package by.gsu.epamlab.model.exceptions;

/**
 * Created by alex on 09.06.2016.
 */
public class DaoException extends Exception {
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message) {
        super(message);
    }
}
