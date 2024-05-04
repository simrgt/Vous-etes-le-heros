package game;

import representation.Event;
import representation.node.NodeFactory;
import ui.Ui;

public class Classic implements Game {
    private final Ui ui;

    public Classic(Ui ui) {
        this.ui = ui;
    }

    @Override
    public void startNewGame() {
        ui.show("Starting new game...");
        ui.show("Loading game...");

        // Code du jeu ici
        Event firstNode = NodeFactory.createStartNode();
        ui.show("Game loaded.\n");
        play(firstNode);
        ui.show("End.");
    }

    private void play(Event currentNode) {
        while (!currentNode.isTerminal()) {
            ui.show(currentNode.display());
            int choice = ui.ask();
            currentNode = currentNode.getNextNode(choice);
        }
        ui.show(currentNode.display()); // Display the terminal node
    }
}
