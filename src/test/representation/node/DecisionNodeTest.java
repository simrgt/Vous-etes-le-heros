package representation.node;

import org.junit.jupiter.api.Test;
import representation.Event;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DecisionNodeTest {

    @Test
    public void testGetNextNode() {
        // Initialize the DecisionNode with some children
        List<Integer> children = Arrays.asList(1, 2, 3);
        DecisionNode node = new DecisionNode(0, "Test", children, 0, "attribute");

        // Test that the correct child is returned for a valid choice
        Event nextNode = node.getNextNode(2);
        assertEquals(2, nextNode.getId());

        // Test that null is returned for an invalid choice
        assertNull(node.getNextNode(4));
    }
}