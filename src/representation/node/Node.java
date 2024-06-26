package representation.node;

import representation.Event;

import java.util.List;

/**
 * Represents a node in the game.
 */
public abstract class Node implements Event {
    private final int id;
    private final String displayed_text;
    private final int nextNode;
    private final int value;
    private final String attribute;

    /**
     * @param id id of the node
     * @param displayed_text text to display
     * @param children children of the node
     */
    public Node(int id, String displayed_text, List<Integer> children, int value, String attribute) {
        this.id = id;
        this.displayed_text = displayed_text;
        this.value = value;
        this.attribute = attribute;
        this.nextNode = children.get(0);
    }

    /**
     * @return the text to display
     */
    public String display() {
        return displayed_text;
    }

    /**
     * @return true if the event is terminal
     */
    public boolean isTerminal() {
        return false;
    }

    /**
     * @param choice user choice (not used)
     * @return the next event
     */
    public Event getNextNode(int choice) {
        return createNode(nextNode);
    }

    protected final Event createNode(int id) {
        return EventFactory.createNode(id);
    }

    public int getValue() {
        return value;
    }

    public String getAttribute() {
        return attribute;
    }

    public int getId() {
        return id;
    }
}
