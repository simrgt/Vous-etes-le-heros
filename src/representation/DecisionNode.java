package representation;

import java.util.HashMap;
import java.util.Map;

public class DecisionNode implements Event {
    private final Map<Integer, Node> nextNodes;

    public DecisionNode() {
        super();
        this.nextNodes = new HashMap<>();
    }

    // Méthode pour ajouter une option de décision
    public void addNextNode(int choice, Node node) {
        nextNodes.put(choice, node);
    }


    @Override
    public String display() {
        StringBuilder sb = new StringBuilder();
        sb.append("Choisissez votre action :\n");
        for (Map.Entry<Integer, Node> entry : nextNodes.entrySet()) {
            sb.append(entry.getKey()).append(". ");
        }
        return sb.toString();
    }

    @Override
    public Event chooseNext() {
        return null;
    }
}
