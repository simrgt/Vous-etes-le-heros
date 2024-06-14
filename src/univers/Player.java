package univers;

import exception.PlayerAttributeException;
import univers.player.Character;

import java.util.List;

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

    int getAttribute(String attribute) throws PlayerAttributeException;

    boolean isDead();

    int interact(int value, String attribute);
}
