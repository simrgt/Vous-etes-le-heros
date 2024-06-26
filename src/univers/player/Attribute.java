package univers.player;

/**
 * Represents an attribute of a player.
 */
public enum Attribute {
    /**
     * Health attribute.
     */
    HEALTH("health"),
    /**
     * Strength attribute.
     */
    STRENGTH("strength"),
    /**
     * Dexterity attribute.
     */
    DEXTERITY("dexterity"),
    /**
     * Intelligence attribute.
     */
    INTELLIGENCE("intelligence"),
    /**
     * Charisma attribute.
     */
    CHARISMA("charisma"),
    /**
     * Luck attribute.
     */
    LUCK("luck");

    /**
     * Name of the attribute.
     */
    private final String name;

    /**
     * @param name name of the attribute
     */
    Attribute(String name) {
        this.name = name;
    }

    /**
     * @return the name of the attribute
     */
    public String getName() {
        return name;
    }
}
