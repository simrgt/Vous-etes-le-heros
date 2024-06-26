package representation;

/**
 * Represents an event in the game.
 */
public interface Event {
    String replace_text = "#&#";
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

    /**
     * @return the value of the event
     */
    int getValue();

    /**
     * @return the attribute of the event
     */
    String getAttribute();

    /**
     * @return the id of the event
     */
    int getId();
}
