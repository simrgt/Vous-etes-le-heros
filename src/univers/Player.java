package univers;

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
}
