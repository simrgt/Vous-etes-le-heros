package game;

import representation.Event;
import representation.node.EventFactory;
import ui.Ui;
import univers.Player;
import univers.player.NormalPlayer;

import java.lang.reflect.Field;
import java.util.List;

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
        Player player = createPlayer();

        // Code du jeu ici
        Event firstNode = EventFactory.createStartNode();
        ui.show("Game loaded.\n");
        play(firstNode, player);
        ui.show("End.");
    }

    /**
     * @param currentNode current node
     */
    private void play(Event currentNode, Player player) {
        while (!currentNode.isTerminal()) {
            ui.clear();
            ui.show(currentNode.display());
            int choice = ui.ask();
            Event nextNode = currentNode.getNextNode(choice);
            String attribute = currentNode.getAttribute();

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

    private Player createPlayer() {
        ui.show("Choisissez votre personnage :");
        List<String> characters = Player.listOfCharacters();
        StringBuilder charactersString = new StringBuilder();
        for (int i = 0; i < characters.size(); i++) {
            charactersString.append("\t").append(i + 1).append(". ").append(characters.get(i));
        }
        int characterChoice;
        do {
            ui.show(charactersString.toString());
            characterChoice = ui.ask();
            if (characterChoice < 1 || characterChoice > characters.size()) {
                ui.show("Choix invalide. Veuillez réessayer.");
            }
        } while (characterChoice < 1 || characterChoice > characters.size());

        ui.show("Votre personnage est : " + characters.get(characterChoice - 1) + "\n");
        boolean validName = false;
        String name = "";
        do {
            ui.show("Entrez votre nom :");
            name = ui.askString();
            if (name.isEmpty()) {
                ui.show("Nom invalide. Veuillez réessayer.");
            } else {
                ui.show("Votre nom est : " + name);
                ui.show("Confirmez-vous ?");
                ui.show("\t1. Oui\n\t2. Non");
                if (ui.ask() == 1) {
                    validName = true;
                }
            }
        } while (!validName);
        return new NormalPlayer("", characters.get(characterChoice - 1));
    }
}
