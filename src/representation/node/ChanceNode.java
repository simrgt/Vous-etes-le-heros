package representation.node;

import representation.Event;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Represents a chance node.
 */
public class ChanceNode extends InnerNode {
    /**
     * Choices of the node.
     */
    private final HashMap<Integer, Integer> choices;
    /**
     * Random object.
     */
    private final Random random = new Random();

    /**
     * @param id id of the node
     * @param displayed_text text to display
     * @param children children of the node
     * @param value     value of the node
     * @param attribute attribute of the node
     */
    public ChanceNode(int id, String displayed_text, List<Integer> children, int value, String attribute) {
        super(id, displayed_text, children, value, attribute);
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
        // Ignore the choice parameter and select a child node at random
        int randomChoice = random.nextInt(choices.size()) + 1;
        return super.createNode(choices.get(randomChoice));
    }
}
