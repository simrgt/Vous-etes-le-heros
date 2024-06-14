package game;

import exception.PlayerAttributeException;
import representation.Event;
import representation.node.EventFactory;
import ui.Ui;
import univers.Player;
import univers.player.NormalPlayer;

import java.io.IOException;
import java.util.List;

/**
 * Represents a classic game.
 */
public class Classic implements Game {
    private final Ui ui;

    public Classic(Ui ui) {
        this.ui = ui;
    }
    /**
     * Starts a new game.
     */
    @Override
    public void startNewGame() throws IOException {
        ui.show("Bienvenue dans le jeu !\n\nChoisis une option:\n\t1. Démarrer une partie\n\t2. Quitter");
        int choice = ui.ask();
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
    private void startGame() throws IOException {
        //ui.show("Starting new game...\nLoading game...");
        Player player = createPlayer();
        try {
            Event firstNode = EventFactory.createStartNode();
            play(firstNode, player);
            ui.show("End.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ui.show("Error while playing game.");
        }

    }


    /**
     * @param currentNode current node
     * @param player player
     */
    private void play(Event currentNode, Player player) throws IOException {
        while (!currentNode.isTerminal()) {
            StringBuilder sb = new StringBuilder();
            try {
                int lostLifePoint = Math.abs(player.interact(currentNode.getValue(), currentNode.getAttribute()));
                sb.append(currentNode.display().replace(Event.replace_text, String.valueOf(lostLifePoint))).append("\n");
            } catch (PlayerAttributeException e) {
                sb.append(currentNode.display()).append("\n");
            }
            sb.append("Vos points de vie : ").append(player.getAttribute("health"));

            if (player.isDead()) {
                ui.show("Vous êtes mort.\nFin de la partie.");
                return;
            }
            ui.show(sb.toString());

            int choice;
            choice = ui.ask();
            Event nextNode = currentNode.getNextNode(choice);

            // Redemander un choix tant que getNextNode renvoie null
            while (nextNode == null) {
                ui.show("Choix invalide. Veuillez réessayer.");
                choice = ui.ask();
                nextNode = currentNode.getNextNode(choice);
            }
            currentNode = nextNode;
        }
        ui.show(currentNode.display() + "\nFin de la partie" + END_CODE); // Display the terminal node
    }

    private Player createPlayer() throws IOException {
        // Select character
        List<String> characters = Player.listOfCharacters();
        StringBuilder charactersString = new StringBuilder();
        charactersString.append("Choisissez votre personnage :\n");
        for (int i = 0; i < characters.size(); i++) {
            charactersString.append("\t").append(i + 1).append(". ").append(characters.get(i));
        }
        int characterChoice;
        ui.show(charactersString.toString());

        boolean valid = false;
        do {
            characterChoice = ui.ask();
            if (characterChoice < 1 || characterChoice > characters.size())
                ui.show("Choix invalide. Veuillez réessayer.\n" + charactersString);
            else {
                ui.show("Votre personnage est : " + characters.get(characterChoice - 1) + "\n" +
                        "Confirmez-vous ?\n" +
                        "\t1. Oui\t2. Non");
                int confirm = ui.ask();
                if (confirm == 1) valid = true;
                else ui.show("Choix invalide. Veuillez réessayer.\n" + charactersString);
            }
        } while (!valid);

        // Enter Name
        valid = false;
        String name = "";
        ui.show("Entrez votre nom :");
        do {
            name = ui.askString();
            if (name.isEmpty()) ui.show("Nom invalide. Veuillez réessayer.\nEntrez votre nom :");
            else {
                String sb = "Votre nom est : " + name + "\nConfirmez-vous ?\n\t1. Oui\t2. Non";
                ui.show(sb);
                int confirm = ui.ask();
                if (confirm == 1) valid = true;
                else ui.show("Choix invalide. Veuillez réessayer.\nEntrez votre nom :");
            }
        } while (!valid);

        return new NormalPlayer("", characters.get(characterChoice - 1));
    }
}
