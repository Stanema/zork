package cz.cvut.oop.game.enemy;

import cz.cvut.oop.game.item.Item;

public class EnemyBuilder {
    private EnemyFlyweight enemyFlyweight;
    private String type;
    private Item loot;

    public EnemyBuilder enemy(EnemyFlyweight enemyFlyweight){
        this.enemyFlyweight = enemyFlyweight;
        return this;
    }

    public EnemyBuilder type(String type){
        this.type = type;
        return this;
    }

    public EnemyBuilder loot(Item loot){
        this.loot = loot;
        return this;
    }

    public Enemy build(){
        return new Enemy(enemyFlyweight, type, loot);
    }
}


