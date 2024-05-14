package representation.node;

import representation.Event;

import java.util.List;

/**
 * Represents a terminal node in the game.
 */
public class TerminalNode extends Node {

    /**
     * @param id id of the node
     * @param displayed_text text to display
     * @param children children of the node
     */
    public TerminalNode(int id, String displayed_text, List<Integer> children) {
        super(id, displayed_text, children);
    }

    /**
     * @return true if the event is terminal
     */
    @Override
    public boolean isTerminal() {
        return true;
    }

    /**
     * @param choice user choice (not used)
     * @return the next event
     */
    @Override
    public Event getNextNode(int choice) {
        return null;
    }
}
