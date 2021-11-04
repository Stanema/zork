package cz.cvut.oop.game;

import cz.cvut.oop.game.enemy.Enemy;
import cz.cvut.oop.game.enemy.EnemyFlyweight;
import cz.cvut.oop.game.item.Item;

import java.util.Collection;

public interface Room {

    String getName();
    String getDescription();
    String getDescriptionWithExits();
    Collection<Room> getExits();
    Room getExitByName(String name);
    Enemy getEnemy();
    Item getItem();
    boolean isLocked();
    void unlock();
    void setEnemy(Enemy enemy);
    void setItem(Item item);
    void registerExit(Room room);
}
