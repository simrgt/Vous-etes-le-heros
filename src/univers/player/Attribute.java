package univers.player;

public enum Attribute {
    HEALTH("health"),
    STRENGTH("strength"),
    DEXTERITY("dexterity"),
    INTELLIGENCE("intelligence"),
    CHARISMA("charisma"),
    LUCK("luck");

    private final String name;

    Attribute(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
