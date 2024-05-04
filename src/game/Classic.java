package game;

import representation.Event;
import representation.node.EventFactory;
import ui.Ui;

public class Classic implements Game {
    private final Ui ui;

    public Classic(Ui ui) {
        this.ui = ui;
    }

    @Override
    public void startNewGame() {
        int choice = gameMenu();
        switch (choice) {
            case 1:
                startGame();
                break;
            case 2:
                ui.show("Exiting game...");
                break;
            default:
                ui.show("Invalid choice. Exiting game...");
        }
    }

    private void startGame() {
        ui.show("Starting new game...");
        ui.show("Loading game...");

        // Code du jeu ici
        Event firstNode = EventFactory.createStartNode();
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

    private int gameMenu() {
        ui.show("Welcome to the game !\nChoose an option:\n\t1. Start new game\n\t2. Exit");
        return ui.ask();
    }
}
