package representation.node;

import representation.Event;

import java.util.List;

public abstract class Node implements Event {
    private final int id;
    private final String displayed_text;
    private final int nextNode;

    public Node(int id, String displayed_text, List<Integer> children) {
        this.id = id;
        this.displayed_text = displayed_text;
        this.nextNode = children.get(0);
    }

    public String display() {
        return displayed_text;
    }

    public boolean isTerminal() {
        return false;
    }

    public Event getNextNode(int choice) {
        return EventFactory.createNode(nextNode);
    }
}
