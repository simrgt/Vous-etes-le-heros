package univers;

import exception.PlayerAttributeException;
import univers.player.Character;

import java.util.List;

/**
 * Represents a player in the game.
 */
public interface Player {
    /**
     * @return name of the player
     */
    String getName();

    /**
     * @return list of characters possible for the player
     */
    static List<String> listOfCharacters() {
        return Character.valuesString();
    }

    /**
     * @param attribute attribute to get
     * @return value of the attribute
     * @throws PlayerAttributeException if the attribute is invalid
     */
    int getAttribute(String attribute) throws PlayerAttributeException;

    /**
     * @return true if the player is dead, false otherwise
     */
    boolean isDead();

    /**
     * @param value value to interact with
     * @param attribute attribute to interact with
     * @return the result of the interaction
     */
    int interact(int value, String attribute);

    String getCharacter();

    void updateHealthPoints(int healthPoints);
}
