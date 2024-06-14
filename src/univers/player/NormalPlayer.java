package univers.player;

import exception.PlayerAttributeException;
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

    /**
     * @param attribute attribute to get
     * @return value of the attribute
     */
    public int getAttribute(String attribute) throws PlayerAttributeException{
        Attribute aatr;
        try {
            aatr = Attribute.valueOf(attribute.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new PlayerAttributeException(attribute);
        }
        return switch (aatr) {
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

    public boolean isDead() {
        return healthPoints <= 0;
    }

    @Override
    public int interact(int value, String attribute) {
        if (attribute != null) {
            Attribute attr = Attribute.valueOf(attribute.toUpperCase());
            int result = 0;
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
        throw new PlayerAttributeException(attribute);
    }
}
