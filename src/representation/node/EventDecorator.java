package representation.node;

import representation.Event;

public abstract class EventDecorator implements Event {
    private final Event event;

    public EventDecorator(Event event) {
        this.event = event;
    }

    @Override
    public String display() {
        return event.display();
    }

    @Override
    public boolean isTerminal() {
        return event.isTerminal();
    }

    @Override
    public Event getNextNode(int choice) {
        return event.getNextNode(choice);
    }
}
