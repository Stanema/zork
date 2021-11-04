package cz.cvut.oop.game.item;

public class CommonItem implements Item{

    String name;

    public CommonItem(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int statsMin() {
        return 0;
    }

    @Override
    public int statsMax() {
        return 0;
    }

    @Override
    public int weight() {
        return 1;
    }
}
