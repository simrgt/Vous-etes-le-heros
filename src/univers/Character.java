package univers;

public abstract class Character implements Player {
    private final String name;
    private int lifePoint;
    private final int attackPoint;
    private final int defensePoint;
    private final int chancePoint;


    public Character(String name, int lifePoint, int attackPoint, int defensePoint, int chancePoint) {
        this.name = name;
        this.lifePoint = lifePoint;
        this.attackPoint = attackPoint;
        this.defensePoint = defensePoint;
        this.chancePoint = chancePoint;
    }

    public String getName() {
        return name;
    }

    public int getLifePoint() {
        return lifePoint;
    }

    public void addLifePoint(int lifePoint) {
        this.lifePoint += lifePoint;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public int getDefensePoint() {
        return defensePoint;
    }

    public int getChancePoint() {
        return chancePoint;
    }
}
