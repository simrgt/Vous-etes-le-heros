package db;

import java.sql.SQLException;

public interface DataBase {
    public NodeModel getNode(int parent_id) throws SQLException;
}
