package cz.cvut.oop.game.item;

public class Weapon implements Item{

    String name;
    int statsMin;
    int statsMax;

    public Weapon(String name, int statsMin, int statsMax) {
        this.name = name;
        this.statsMin = statsMin;
        this.statsMax = statsMax;
    }

    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public int statsMin() {
        return statsMin;
    }
    @Override
    public int statsMax() {
        return statsMax;
    }

    @Override
    public int weight() {
        return 0;
    }
}
