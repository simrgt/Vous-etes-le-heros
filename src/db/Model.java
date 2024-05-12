package db;

import java.sql.*;

public abstract class Model {
    private final static Connection c;

    static {
        try {
            c = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected static Connection getConnection() {
        return c;
    }
}
