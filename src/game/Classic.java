package game;

import exception.PlayerAttributeException;
import representation.Event;
import representation.node.EventFactory;
import univers.Player;
import univers.player.NormalPlayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Represents a classic game.
 */
public class Classic implements Game {
    private final DataOutputStream dos;
    private final DataInputStream dis;

    public Classic(DataOutputStream dos, DataInputStream dis) {
        this.dos = dos;
        this.dis = dis;
    }
    /**
     * Starts a new game.
     */
    @Override
    public void startNewGame() throws IOException {
        dos.writeUTF("Bienvenue dans le jeu !\n\nChoisis une option:\n\t1. Démarrer une partie\n\t2. Quitter");
        int choice = Integer.parseInt(dis.readUTF());
        switch (choice) {
            case 1:
                startGame();
                break;
            case 2:
                dos.writeUTF("Exiting game...");
                break;
            default:
                dos.writeUTF("Invalid choice. Exiting game...");
        }
    }

    /**
     * Starts the game.
     */
    private void startGame() throws IOException {
        //dos.writeUTF("Starting new game...\nLoading game...");
        Player player = createPlayer();
        try {
            Event firstNode = EventFactory.createStartNode();
            play(firstNode, player);
            dos.writeUTF("End.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            dos.writeUTF("Error while playing game.");
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
                dos.writeUTF("Vous êtes mort.\nFin de la partie.");
                return;
            }
            dos.writeUTF(sb.toString());

            int choice;
            choice = convertToInt(dis.readUTF());
            Event nextNode = currentNode.getNextNode(choice);

            // Redemander un choix tant que getNextNode renvoie null
            while (nextNode == null) {
                dos.writeUTF("Choix invalide. Veuillez réessayer.");
                choice = convertToInt(dis.readUTF());
                nextNode = currentNode.getNextNode(choice);
            }
            currentNode = nextNode;
        }
        dos.writeUTF(currentNode.display() + "\nFin de la partie" + END_CODE); // Display the terminal node
    }

    private int convertToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return -1;
        }
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
        dos.writeUTF(charactersString.toString());

        boolean valid = false;
        do {
            characterChoice = Integer.parseInt(dis.readUTF());
            if (characterChoice < 1 || characterChoice > characters.size())
                dos.writeUTF("Choix invalide. Veuillez réessayer.\n" + charactersString);
            else {
                dos.writeUTF("Votre personnage est : " + characters.get(characterChoice - 1) + "\n" +
                        "Confirmez-vous ?\n" +
                        "\t1. Oui\t2. Non");
                int confirm = Integer.parseInt(dis.readUTF());
                if (confirm == 1) valid = true;
                else dos.writeUTF("Choix invalide. Veuillez réessayer.\n" + charactersString);
            }
        } while (!valid);

        // Enter Name
        valid = false;
        String name = "";
        dos.writeUTF("Entrez votre nom :");
        do {
            name = dis.readUTF();
            if (name.isEmpty()) dos.writeUTF("Nom invalide. Veuillez réessayer.\nEntrez votre nom :");
            else {
                String sb = "Votre nom est : " + name + "\nConfirmez-vous ?\n\t1. Oui\t2. Non";
                dos.writeUTF(sb);
                int confirm = Integer.parseInt(dis.readUTF());
                if (confirm == 1) valid = true;
                else dos.writeUTF("Choix invalide. Veuillez réessayer.\nEntrez votre nom :");
            }
        } while (!valid);

        return new NormalPlayer("", characters.get(characterChoice - 1));
    }
}
