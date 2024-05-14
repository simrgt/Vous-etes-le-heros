package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Represents a model in the database.
 */
public abstract class Model {
    private final static Connection c;

    static {
        try {
            c = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return the connection to the database
     */
    protected static Connection getConnection() {
        return c;
    }
}
