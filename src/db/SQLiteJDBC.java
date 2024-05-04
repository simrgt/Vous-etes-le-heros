package db;

import java.sql.*;

public class SQLiteJDBC implements DataBase {
    private Connection c = DriverManager.getConnection("jdbc:sqlite:database.db");

    public SQLiteJDBC() throws SQLException {
    }

    public NodeModel getNode(int parent_id) {
        // get query from file get_random_node.sql and replace ? with nodeType
        String query = "SELECT parent_node.ID, parent_node.DISPLAYED_TEXT, " +
                "parent_node.type, GROUP_CONCAT(child_node.ID) AS CHILD_ID " +
                "FROM NODE AS parent_node LEFT JOIN NODE AS child_node ON parent_node.ID = child_node.PARENT " +
                "WHERE parent_node.id = ? " +
                "GROUP BY parent_node.ID " +
                "ORDER BY RANDOM() LIMIT 1;";
        try (PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setInt(1, parent_id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new NodeModel(
                            rs.getInt("id"),
                            rs.getString("displayed_text"),
                            rs.getString("child_id"),
                            rs.getString("type")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
