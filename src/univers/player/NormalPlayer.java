package univers.player;

import univers.Player;

public class NormalPlayer implements Player {
    private final String name;
    private final Character character;
    private int healthPoints;

    /**
     * @param name name of the player
     * @param character character of the player
     */
    public NormalPlayer(String name, String character) {
        this.name = name;
        this.character = Character.valueOf(character);
        this.healthPoints = this.character.getHealthPoints();
    }

    /**
     * @return name of the player
     */
    public String getName() {
        return name;
    }
}
