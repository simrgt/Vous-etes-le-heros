package representation.node;

import representation.Event;
import representation.Node;

public class StartNode extends Node {
    private Event nextNode;
    private static final String text = "Bienvenue dans les contrées de l'imaginaire. Vous êtes un aventurier " +
            "intrépide, prêt à affronter les dangers les plus terribles pour sauver le royaume.\n" +
            "Vous voilà parti pour une aventure épique, où vous devrez faire des choix qui détermineront votre " +
            "destinée.\n";
    @Override
    public Event chooseNext() {
        return this;
    }

    @Override
    public String display() {
        return text;
    }

    @Override
    public void addNextNode(Event node) {
        this.nextNode = node;
    }
}
