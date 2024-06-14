package representation.node;

import representation.Event;

/**
 * Represents a decorator for an event.
 */
public abstract class EventDecorator implements Event {
    private final Event event;

    /**
     * @param event event to decorate
     */
    public EventDecorator(Event event) {
        this.event = event;
    }

    /**
     * @return the text to display
     */
    @Override
    public String display() {
        return event.display();
    }

    /**
     * @return true if the event is terminal
     */
    @Override
    public boolean isTerminal() {
        return event.isTerminal();
    }

    /**
     * @param choice user choice
     * @return the next event
     */
    @Override
    public Event getNextNode(int choice) {
        return event.getNextNode(choice);
    }

    @Override
    public int getValue() {
        return event.getValue();
    }

    @Override
    public String getAttribute() {
        return event.getAttribute();
    }
}
