package univers.player;

import java.util.ArrayList;
import java.util.List; /**
 * Represents different types of characters in the game like Human, Elf, Orc, etc.
 * They have different abilities and characteristics, like health points, chance of success, etc.
 * They can also have different interactions with the environment.
 * They can be controlled by the player.
 * Get all the values of the characters from database in table Character.
 */
public enum Character {
    // Health points, strength, dexterity, intelligence, charisma, luck different for each character
    HUMAN(100, 10, 10, 10, 10, 10),
    ELF(80, 5, 15, 15, 10, 10),
    ORC(120, 15, 5, 5, 5, 10),
    DWARF(90, 10, 10, 10, 5, 15);

    private final int healthPoints;
    private final int strength;
    private final int dexterity;
    private final int intelligence;
    private final int charisma;
    private final int luck;

    /**
     * @param healthPoints health points
     * @param strength strength
     * @param dexterity dexterity
     * @param intelligence intelligence
     * @param charisma charisma
     * @param luck luck
     */
    Character(int healthPoints, int strength, int dexterity, int intelligence, int charisma, int luck) {
        this.healthPoints = healthPoints;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.charisma = charisma;
        this.luck = luck;
    }

    /**
     * @return list of characters possible for the player dynamically
     */
    public static List<String> valuesString() {
        List<String> values = new ArrayList<>();
        for (Character character : Character.values()) {
            values.add(character.toString());
        }
        return values;
    }

    /**
     * @return health points
     */
    public int getHealthPoints() {
        return healthPoints;
    }

    /**
     * @return strength
     */
    public int getStrength() {
        return strength;
    }

    /**
     * @return dexterity
     */
    public int getDexterity() {
        return dexterity;
    }

    /**
     * @return intelligence
     */
    public int getIntelligence() {
        return intelligence;
    }

    /**
     * @return charisma
     */
    public int getCharisma() {
        return charisma;
    }

    /**
     * @return luck
     */
    public int getLuck() {
        return luck;
    }
}