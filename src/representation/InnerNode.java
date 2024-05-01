package representation;

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
    public String display() {
        return null;
    }
}
