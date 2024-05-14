package representation.node;

import java.util.List;

/**
 * Represents a start node in the game.
 */
public class StartNode extends Node {

    /**
     * @param id id of the node
     * @param displayed_text text to display
     * @param children children of the node
     */
    public StartNode(int id, String displayed_text, List<Integer> children) {
        super(id, displayed_text, children);
    }
}
