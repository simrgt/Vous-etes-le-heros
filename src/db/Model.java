package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Represents a model in the database.
 */
public abstract class Model {
    private static final String DB_URL = "jdbc:sqlite:database.db";

    /**
     * @return a new connection to the database
     * @throws SQLException if a database access error occurs
     */
    protected static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}
