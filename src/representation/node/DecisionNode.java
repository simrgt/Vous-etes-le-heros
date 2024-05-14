package representation.node;

import representation.Event;

import java.util.HashMap;
import java.util.List;

/**
 * Represents a decision node in the game.
 */
public class DecisionNode extends InnerNode {
    private final HashMap<Integer, Integer> choices;

    /**
     * @param id id of the node
     * @param displayed_text text to display
     * @param children children of the node
     */
    public DecisionNode(int id, String displayed_text, List<Integer> children) {
        super(id, displayed_text, children);
        choices = new HashMap<>();
        for (int i = 0; i < children.size(); i++) {
            choices.put(i+1, children.get(i));
        }
    }

    /**
     * @param choice user choice
     * @return the next event
     */
    @Override
    public Event getNextNode(int choice) {
        if (!choices.containsKey(choice)) {
            return null;
        }
        return EventFactory.createNode(choices.get(choice));
    }


}
