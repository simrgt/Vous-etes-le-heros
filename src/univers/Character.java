package univers;

public abstract class Character implements Player {
    private final String name;
    private final int lifePoint;


    public Character(String name, int lifePoint) {
        this.name = name;
        this.lifePoint = lifePoint;
    }
}
