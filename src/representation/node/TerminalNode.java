package representation.node;

import representation.Event;
import representation.Node;

public class TerminalNode extends Node {
    private static final String text = "Vous avez vaincu le dragon et avez rendu la paix au pays.";
    @Override
    public Event chooseNext() {
        return this;
    }

    @Override
    public void addNextNode(Event node) {}

    @Override
    public String display() {
        return text;
    }
}
