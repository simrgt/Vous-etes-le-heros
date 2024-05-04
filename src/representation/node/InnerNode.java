package representation.node;

import representation.Event;
import representation.Node;

import java.util.List;

public class InnerNode extends Node {
    private List<Event> listEvent;

    public InnerNode() {
        super();
    }
    @Override
    public Event chooseNext() {
        return this;
    }

    @Override
    public void addNextNode(Event node) {
        listEvent.add(node);
    }

    @Override
    public String display() {
        return null;
    }
}
