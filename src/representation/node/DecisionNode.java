package representation.node;

import representation.Event;

import java.util.HashMap;
import java.util.List;

public class DecisionNode extends InnerNode {
    private final HashMap<Integer, Integer> choices;
    public DecisionNode(int id, String displayed_text, List<Integer> children) {
        super(id, displayed_text, children);
        choices = new HashMap<>();
        for (int i = 0; i < children.size(); i++) {
            choices.put(i+1, children.get(i));
        }
    }

    @Override
    public Event getNextNode(int choice) {
        if (!choices.containsKey(choice)) {
            return null;
        }
        return NodeFactory.createNode(choices.get(choice));
    }


}
