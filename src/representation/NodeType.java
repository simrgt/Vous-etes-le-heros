package representation;

import representation.node.*;

public enum NodeType {
    INNER_NODE(InnerNode.class),
    TERMINAL_NODE(TerminalNode.class),
    DECISION_NODE(DecisionNode.class),
    CHANCE_NODE(ChanceNode.class),
    START_NODE(StartNode.class);

    private final Class<? extends Node> nodeClass;

    NodeType(Class<? extends Node> nodeClass) {
        this.nodeClass = nodeClass;
    }

    public Class<? extends Node> getNodeClass() {
        return nodeClass;
    }

}

