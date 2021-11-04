package cz.cvut.oop.game.enemy;


import cz.cvut.oop.game.item.Item;

public class Enemy {
    private EnemyFlyweight enemyFlyweight;
    private String type;
    private Item loot;

    public String getType() {
        return type;
    }


    public Item getLoot() {
        return loot;
    }


    public static EnemyBuilder builder() {
        return new EnemyBuilder();
    }

    public Enemy(EnemyFlyweight enemyFlyweight, String type, Item loot) {
        this.enemyFlyweight = enemyFlyweight;
        this.type = type;
        this.loot = loot;
    }

    public EnemyFlyweight getEnemyFlyweight() {
        return this.enemyFlyweight;
    }
}

