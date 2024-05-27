package game;

import representation.Event;
import representation.node.EventFactory;
import ui.Ui;

/**
 * Represents a classic game.
 */
public class Classic implements Game {
    private final Ui ui;

    /**
     * @param ui user interface
     */
    public Classic(Ui ui) {
        this.ui = ui;
    }

    /**
     * Starts a new game.
     */
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

    /**
     * Starts the game.
     */
    private void startGame() {
        ui.show("Starting new game...");
        ui.show("Loading game...");

        // Code du jeu ici
        Event firstNode = EventFactory.createStartNode();
        ui.show("Game loaded.\n");
        play(firstNode);
        ui.show("End.");
    }

    /**
     * @param currentNode current node
     */
    private void play(Event currentNode) {
        while (!currentNode.isTerminal()) {
            ui.clear();
            ui.show(currentNode.display());
            int choice = ui.ask();
            Event nextNode = currentNode.getNextNode(choice);

            // Redemander un choix tant que getNextNode renvoie null
            while (nextNode == null) {
                ui.show("Choix invalide. Veuillez réessayer.");
                choice = ui.ask();
                nextNode = currentNode.getNextNode(choice);
            }
            currentNode = nextNode;
        }
        ui.show(currentNode.display()); // Display the terminal node
    }

    /**
     * @return user choice
     */
    private int gameMenu() {
        ui.show("Welcome to the game !\nChoose an option:\n\t1. Start new game\n\t2. Exit");
        return ui.ask();
    }
}
