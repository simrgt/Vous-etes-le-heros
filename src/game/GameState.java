package game;

import db.GameStateModel;
import representation.Event;
import univers.Player;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class GameState {
    private int id;
    private Player player;
    private Event event;

    public GameState(Player player, Event event) {
        try {
            this.player = player;
            this.event = event;
            this.id = GameStateModel.saveGameState(player, event);
        } catch (SQLException e) {
            System.out.println("Error while saving game state: " + e.getMessage());
        }
    }

    public GameState(int id, Player player, Event event) {
        this.id = id;
        this.player = player;
        this.event = event;
    }

    public static Map<Integer, String> getSavedGames() {
        try {
            return GameStateModel.getSavedGames();
        } catch (SQLException e) {
            System.out.println("Error while loading saved games: " + e.getMessage());
            return new HashMap<>();
        }
    }

    public int getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void updateGameState() {
        try {
            GameStateModel.updateGameState(id, player, event);
        } catch (SQLException e) {
            System.out.println("Error while updating game state: " + e.getMessage());
        }
    }

    public void deleteGameState() {
        try {
            GameStateModel.deleteGameState(id);
        } catch (SQLException e) {
            System.out.println("Error while deleting game state: " + e.getMessage());
        }
    }
}
