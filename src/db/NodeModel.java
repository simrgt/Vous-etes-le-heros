package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a node in the database.
 */
public class NodeModel extends Model {
    private final int id;
    private final String displayed_text;
    private final List<Integer> children;
    private final String type;
    private final String sound_path;
    private final String image_path;

    /**
     * @param id           id of the node
     * @param displayed_text text to display
     * @param children    children of the node
     * @param type        type of the node
     * @param sound_path path to the sound file
     * @param image_path path to the image file
     */
    private NodeModel(int id, String displayed_text, String children, String type, String sound_path, String image_path) {
        this.id = id;
        this.displayed_text = displayed_text;
        if ((children == null) || children.isEmpty()) this.children = List.of(Integer.MAX_VALUE);
        else this.children = Arrays.stream(children.split(",")).map(Integer::parseInt).toList();
        this.type = type;
        this.sound_path = sound_path;
        this.image_path = image_path;
    }

    /**
     * @param parent_id id of the parent node
     * @return a random node with the given parent_id
     * @throws SQLException if no node is found with the given parent_id
     */
    public static NodeModel getNode(int parent_id) throws SQLException {
        // get query from file get_random_node.sql and replace ? with nodeType
        String query = "SELECT parent_node.ID, parent_node.DISPLAYED_TEXT, " +
                "parent_node.type, GROUP_CONCAT(child_node.ID) AS CHILD_ID, parent_node.sound_path, parent_node.image_path " +
                "FROM NODE AS parent_node LEFT JOIN NODE AS child_node ON parent_node.ID = child_node.PARENT " +
                "WHERE parent_node.id = ? " +
                "GROUP BY parent_node.ID " +
                "ORDER BY RANDOM() LIMIT 1;";
        PreparedStatement stmt = getConnection().prepareStatement(query);
        stmt.setInt(1, parent_id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new NodeModel(
                    rs.getInt("id"),
                    rs.getString("displayed_text"),
                    rs.getString("child_id"),
                    rs.getString("type"),
                    rs.getString("sound_path"),
                    rs.getString("image_path")
            );
        } else {
            throw new SQLException("No node found with parent_id " + parent_id);
        }
    }

    /**
     * @return id of the node
     */
    public int getId() {
        return id;
    }

    /**
     * @return text to display
     */
    public String getDisplayed_text() {
        return displayed_text;
    }

    /**
     * @return children of the node
     */
    public List<Integer> getChildren() {
        return children;
    }

    /**
     * @return type of the node
     */
    public String getType() {
        return type;
    }

    /**
     * @return path to the sound file
     */
    public String getSound_path() {
        return sound_path;
    }

    /**
     * @return path to the image file
     */
    public String getImage_path() {
        return image_path;
    }
}
