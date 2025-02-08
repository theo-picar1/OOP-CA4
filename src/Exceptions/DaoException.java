package Exceptions;

import java.sql.SQLException;

public class DaoException extends SQLException {
    public DaoException() {
        // Don't know the point of this
    }

    public DaoException(String aMessage) { super(aMessage); }
}
