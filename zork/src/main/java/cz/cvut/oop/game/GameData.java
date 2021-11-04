package cz.cvut.oop.game;

import cz.cvut.oop.game.hero.Hero;

public interface GameData {

    boolean isFinished();
    void setFinished(boolean finished);
    Room getCurrentRoom();
    void setCurrentRoom(Room currentRoom);
    void set();
    Hero getHero();
}
