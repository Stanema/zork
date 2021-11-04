package cz.cvut.oop.game.enemy;

import cz.cvut.oop.game.item.Item;

public class EnemyFlyweight {

    private String name;
    private int HP;
    private int attackMin;
    private int attackMax;


    public EnemyFlyweight(String name, int HP, int attackMin, int attackMax) {
        this.name = name;
        this.HP = HP;
        this.attackMin = attackMin;
        this.attackMax = attackMax;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAttackMin() {
        return attackMin;
    }

    public void setAttackMin(int attackMin) {
        this.attackMin = attackMin;
    }

    public int getAttackMax() {
        return attackMax;
    }

    public void setAttackMax(int attackMax) {
        this.attackMax = attackMax;
    }





}
