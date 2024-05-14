package representation;

/**
 * Represents an event in the game.
 */
public interface Event {
    /**
     * @return the text to display
     */
    String display();

    /**
     * @return true if the event is terminal
     */
    boolean isTerminal();

    /**
     * @param choice user choice
     * @return the next event
     */
    Event getNextNode(int choice);
}
