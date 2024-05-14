package representation.node;


/**
 * Represents the type of node.
 */
public enum NodeType {
    INNER_NODE(InnerNode.class),
    DECISION_NODE(DecisionNode.class),
    //CHANCE_NODE(ChanceNode.class),
    START_NODE(StartNode.class),
    TERMINAL_NODE(TerminalNode.class);

    private final Class<? extends Node> nodeClass;

    /**
     * @param nodeClass class of the node
     */
    NodeType(Class<? extends Node> nodeClass) {
        this.nodeClass = nodeClass;
    }

    /**
     * @return the class of the node
     */
    public Class<? extends Node> getNodeClass() {
        return nodeClass;
    }

}

