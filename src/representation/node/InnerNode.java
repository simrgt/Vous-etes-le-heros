package representation.node;

import java.util.List;

/**
 * Represents an inner node in the game.
 */
public class InnerNode extends Node {

    /**
     * @param id id of the node
     * @param displayed_text text to display
     * @param children children of the node
     */
    public InnerNode(int id, String displayed_text, List<Integer> children) {
        super(id, displayed_text, children);
    }
}
