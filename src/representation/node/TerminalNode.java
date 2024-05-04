package representation.node;

import representation.Event;

import java.util.List;

public class TerminalNode extends Node {

    public TerminalNode(int id, String displayed_text, List<Integer> children) {
        super(id, displayed_text, children);
    }

    @Override
    public boolean isTerminal() {
        return true;
    }

    @Override
    public Event getNextNode(int choice) {
        return null;
    }
}
