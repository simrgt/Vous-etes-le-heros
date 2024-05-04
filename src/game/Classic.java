package game;

import representation.Event;
import representation.NodeFactory;
import ui.Ui;

public class Classic implements Game {
    private final Ui ui;

    public Classic(Ui ui) {
        this.ui = ui;
    }

    @Override
    public void startNewGame() {
        ui.afficher("Starting new game...");
        ui.afficher("Loading game...");

        // Code du jeu ici
        Event firstNode = createGame();
        ui.afficher("Game loaded.\n");

        ui.afficher(firstNode.display());
    }

    private Event createGame() {
        return NodeFactory.createStartNode();
    }
}
