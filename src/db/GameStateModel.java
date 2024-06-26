package db;

import representation.Event;
import representation.node.EventFactory;
import univers.Player;
import univers.player.NormalPlayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameStateModel extends Model {
    public static int saveGameState(Player player, Event currentNode) throws SQLException {
        System.out.println(player.getName());
        String query = "INSERT INTO GAME_STATE (NAME, HEALTH, CHARACTER, CURRENT_NODE_ID) VALUES (?, ?, ?, ?)";
        // Get the ID of the saved game state to return
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, player.getName());
            stmt.setInt(2, player.getAttribute("health"));
            stmt.setString(3, player.getCharacter());
            stmt.setInt(4, currentNode.getId());
            stmt.executeUpdate();
            // Get generated keys
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException("Failed to insert game state, no ID obtained.");
                }
            }
        }
    }

    public static void updateGameState(int id, Player player, Event currentNode) throws SQLException {
        String query = "UPDATE GAME_STATE SET CURRENT_NODE_ID = ?, HEALTH = ? WHERE ID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, currentNode.getId());
            stmt.setInt(2, player.getAttribute("health"));
            stmt.setInt(3, id);
            stmt.executeUpdate();
        }
    }

    public static void deleteGameState(int id) throws SQLException {
        String query = "DELETE FROM GAME_STATE WHERE ID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public static Map<Integer, String> getSavedGames() throws SQLException {
        Map<Integer, String> savedGames = new LinkedHashMap<>();
        String query = "SELECT ID, NAME, CHARACTER, HEALTH FROM GAME_STATE";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("NAME") + " : " + rs.getString("CHARACTER") + " - " + rs.getInt("HEALTH") + " HP";
                savedGames.put(id, name);
            }
        }
        return savedGames;
    }

    public static Player loadGameState(int saveId) throws SQLException {
        String query = "SELECT NAME, HEALTH, CHARACTER FROM GAME_STATE WHERE ID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, saveId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("NAME");
                    int health = rs.getInt("HEALTH");
                    String character = rs.getString("CHARACTER");
                    Player player = new NormalPlayer(name, character);
                    player.updateHealthPoints(health - player.getAttribute("health")); // Set the player's health to the saved health
                    return player;
                } else {
                    throw new SQLException("No saved game state found for ID " + saveId);
                }
            }
        }
    }

    public static Event loadCurrentNode(int saveId) throws SQLException {
        String query = "SELECT CURRENT_NODE_ID FROM GAME_STATE WHERE ID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, saveId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int currentNodeId = rs.getInt("CURRENT_NODE_ID");
                    return EventFactory.createNode(currentNodeId);
                } else {
                    throw new SQLException("No saved game state found for ID " + saveId);
                }
            }
        }
    }
}