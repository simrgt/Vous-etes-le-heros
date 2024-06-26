package game;

import db.GameStateModel;
import exception.PlayerAttributeException;
import representation.Event;
import representation.node.EventFactory;
import ui.Ui;
import univers.Player;
import univers.player.NormalPlayer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Represents a classic game.
 */
public class Classic implements Game {
    /**
     * Ui of the game.
     */
    private final Ui ui;

    private GameState gameState;

    /**
     * @param ui ui of the game
     */
    public Classic(Ui ui) {
        this.ui = ui;
    }

    /**
     * Starts a new game.
     */
    @Override
    public void startNewGame() throws IOException {
        ui.show("Bienvenue dans le jeu !" + ui.getEscape() + ui.getEscape() +
                "Choisis une option:" + ui.getEscape() + ui.getTab() +
                "1. Démarrer une partie" + ui.getEscape() + ui.getTab() +
                "2. Charger une partie" + ui.getEscape() + ui.getTab() +
                "3. Quitter");
        int choice = ui.ask();
        switch (choice) {
            case 1:
                startGame();
                break;
            case 2:
                try {
                    restartGame();
                } catch (SQLException e) {
                    System.out.println("Error while loading game: " + e.getMessage());
                    ui.show("Error while loading game. Exiting game...#!#END#!#");
                }
                break;
            case 3:
                ui.show("Exiting game...#!#END#!#");
                break;
            default:
                ui.show("Invalid choice. Exiting game...#!#END#!#");
        }
    }


    /**
     * @throws SQLException if an error occurs while loading the game
     */
    private void restartGame() throws SQLException {
        // show saved games
        Map<Integer, String> savedGames = GameState.getSavedGames();
        if (savedGames.isEmpty()) {
            ui.show("Aucune partie sauvegardée.");
            ui.askString();
            startGame();
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Choisissez une partie à charger :").append(ui.getEscape());
        for (Map.Entry<Integer, String> entry : savedGames.entrySet()) {
            sb.append(ui.getTab()).append(entry.getKey()).append(". ").append(entry.getValue()).append(ui.getEscape());
        }
        ui.show(sb.toString());
        int choice = ui.ask();
        if (choice < 1 || !savedGames.containsKey(choice)) {
            ui.show("Choix invalide. Veuillez réessayer.");
            restartGame();
            return;
        }
        
        Player player = GameStateModel.loadGameState(choice);
        Event currentNode = GameStateModel.loadCurrentNode(choice);
        gameState = new GameState(choice, player, currentNode);

        play();
    }

    /**
     * Starts the game.
     */
    private void startGame() {
        Player player = createPlayer();
        try {
            Event firstNode = EventFactory.createStartNode();
            gameState = new GameState(player, firstNode);
            play();
            ui.show("End.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ui.show("Error while playing game.");
        }

    }
    private void play() {
        while (!gameState.getEvent().isTerminal()) {
            StringBuilder sb = new StringBuilder();
            try {
                int lostLifePoint = Math.abs(gameState.getPlayer().interact(gameState.getEvent().getValue(), gameState.getEvent().getAttribute()));
                sb.append(gameState.getEvent().display().replace(Event.replace_text, String.valueOf(lostLifePoint)));
            } catch (PlayerAttributeException e) {
                sb.append(gameState.getEvent().display());
            }
            sb.append(ui.getBaliseLife()).append("Vos points de vie : ").append(gameState.getPlayer().getAttribute("health")).append(ui.getBaliseLife());

            if (gameState.getPlayer().isDead()) {
                ui.show("Vous êtes mort. Fin de la partie.");
                gameState.deleteGameState();
                return;
            }
            ui.show(sb.toString());

            int choice;
            choice = ui.ask();
            Event nextNode = gameState.getEvent().getNextNode(choice);

            // Redemander un choix tant que getNextNode renvoie null
            while (nextNode == null) {
                ui.show("Choix invalide. Veuillez réessayer.");
                choice = ui.ask();
                nextNode = gameState.getEvent().getNextNode(choice);
            }
            gameState.setEvent(nextNode);
            gameState.updateGameState();
        }
        ui.show(gameState.getEvent().display() + ui.getEscape() + "Fin de la partie" + END_CODE); // Display the terminal node
        gameState.deleteGameState();
    }

    /**
     * @return a new player
     */
    private Player createPlayer() {
        // Select character
        List<String> characters = Player.listOfCharacters();
        StringBuilder charactersString = new StringBuilder();
        charactersString.append("Choisissez votre personnage :").append(ui.getEscape());
        for (int i = 0; i < characters.size(); i++) {
            charactersString.append(ui.getTab()).append(i + 1).append(". ").append(characters.get(i)).append(ui.getEscape());
        }
        int characterChoice;
        ui.show(charactersString.toString());

        boolean valid = false;
        do {
            characterChoice = ui.ask();
            if (characterChoice < 1 || characterChoice > characters.size())
                ui.show("Choix invalide. Veuillez réessayer." + ui.getEscape() + charactersString);
            else {
                ui.show("Votre personnage est : " + characters.get(characterChoice - 1) + ui.getEscape() +
                        "Confirmez-vous ?" + ui.getEscape() +
                        "\t1. Oui\t2. Non");
                int confirm = ui.ask();
                if (confirm == 1) valid = true;
                else ui.show("Choix invalide. Veuillez réessayer." + ui.getEscape() + charactersString);
            }
        } while (!valid);

        // Enter Name
        valid = false;
        String name;
        ui.show("Entrez votre nom :");
        do {
            name = ui.askString();
            if (name.isEmpty()) ui.show("Nom invalide. Veuillez réessayer." + ui.getEscape() + "Entrez votre nom :");
            else {
                String sb = "Votre nom est : " + name + ui.getEscape() +
                        "Confirmez-vous ?" + ui.getEscape() +
                        ui.getTab() + "1. Oui" + ui.getTab() + "2. Non";
                ui.show(sb);
                int confirm = ui.ask();
                if (confirm == 1) valid = true;
                else ui.show("Choix invalide. Veuillez réessayer." + ui.getEscape() + "Entrez votre nom :");
            }
        } while (!valid);

        return new NormalPlayer(name, characters.get(characterChoice - 1));
    }
}
