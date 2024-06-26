package univers.player;

import exception.PlayerAttributeException;
import univers.Player;

/**
 * Represents a normal player in the game.
 */
public class NormalPlayer implements Player {
    /**
     * Name of the player.
     */
    private final String name;
    /**
     * Character of the player.
     */
    private final Character character;
    /**
     * Health points of the player.
     */
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

    /**
     * @param attribute attribute to get
     * @return value of the attribute
     */
    public int getAttribute(String attribute) throws PlayerAttributeException{
        Attribute attr;
        try {
            attr = Attribute.valueOf(attribute.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new PlayerAttributeException(attribute);
        }
        return switch (attr) {
            case HEALTH -> healthPoints;
            case STRENGTH -> character.getStrength();
            case DEXTERITY -> character.getDexterity();
            case INTELLIGENCE -> character.getIntelligence();
            case CHARISMA -> character.getCharisma();
            case LUCK -> character.getLuck();
        };
    }

    /**
     * @param healthPoints health points to set
     */
    public void updateHealthPoints(int healthPoints) {
        this.healthPoints += healthPoints;
    }

    /**
     * @return true if the player is dead, false otherwise
     */
    public boolean isDead() {
        return healthPoints <= 0;
    }

    /**
     * @param value value to interact with
     * @param attribute attribute to interact with
     * @return the result of the interaction
     * @throws PlayerAttributeException if the attribute is invalid
     */
    @Override
    public int interact(int value, String attribute) throws PlayerAttributeException {
        if (attribute != null) {
            Attribute attr;
            try {
                attr = Attribute.valueOf(attribute.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new PlayerAttributeException(attribute);
            }
            int result;
            switch (attr) {
                case HEALTH:
                    updateHealthPoints(value);
                    return value;
                case STRENGTH:
                    result = Math.min(character.getStrength() - value, 0);
                    break;
                case DEXTERITY:
                    result = Math.min(character.getDexterity() - value, 0);
                    break;
                case INTELLIGENCE:
                    result = Math.min(character.getIntelligence() - value, 0);
                    break;
                case CHARISMA:
                    result = Math.min(character.getCharisma() - value, 0);
                    break;
                case LUCK:
                    result = Math.min(character.getLuck() - value, 0);
                    break;
                default:
                    throw new PlayerAttributeException(attribute);
            }
            updateHealthPoints(result);
            return result;
        }
        throw new PlayerAttributeException();
    }

    /**
     * @return character of the player
     */
    public String getCharacter() {
        return character.toString();
    }
}
