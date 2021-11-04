package cz.cvut.oop.game;

import cz.cvut.oop.game.enemy.*;
import cz.cvut.oop.game.hero.Hero;
import cz.cvut.oop.game.item.CommonItem;
import cz.cvut.oop.game.item.Item;
import cz.cvut.oop.game.item.Weapon;

/**
 *  This class represents immutable game data holder, all mutable game data should exist within this class
 *  e.g. room map, finished, inventory, weapons..
 */
public class GameDataImpl implements GameData {

    private Room currentRoom;
    //LinkedList<Room> map;
    private boolean finished = false;
    Hero hero;

    @Override
    public Hero getHero() {
        return hero;
    }


    /**
     *  Room map registration in constructor
     */
    public GameDataImpl(){
            set();
    }

    @Override
    public void set(){
        this.hero = new Hero(100);

        Room field = new RoomImpl("pole", "Pole u lesa", false);
        Room forrest = new RoomImpl("les", "Hluboký a temný les, v něhož nitru se tyčí vysoká hora ",false);
        Room cave = new RoomImpl("jeskyně", "Tajuplná jeskyně ze které svítí hromada červených očíček", false);
        Room mine = new RoomImpl("důl", "Ponurý důl který se větvý do mnoha malých chodeb a schodišť",false);
        Room stairsDown = new RoomImpl("schody dolů", "Točité shody vedoucí do temnoty",false);
        Room stairsUp = new RoomImpl("schody nahoru", "Točité shody vedoucí do temnoty, ale nahoru",false);
        Room mine2 = new RoomImpl("důlní chodba", "Chodba s kolejemi a vrzavýmy vozíky, která je na konci zasypaná", false);
        Room mine3 = new RoomImpl("důlní místnost", "Místnost ve které je hrozná tma", false);
        Room mountain = new RoomImpl("vrcholek hory", "Vylezl jsi z temné chodby až na úplném vrcholku hory",true);

        field.registerExit(forrest);
        forrest.registerExit(field);
        forrest.registerExit(cave);
        cave.registerExit(forrest);
        cave.registerExit(mine);
        mine.registerExit(cave);
        mine.registerExit(stairsUp);
        mine.registerExit(stairsDown);
        mine.registerExit(mine2);
        stairsUp.registerExit(mine);
        stairsUp.registerExit(mountain);
        stairsDown.registerExit(mine);
        stairsDown.registerExit(mine3);
        mine2.registerExit(mine);
        mine3.registerExit(stairsDown);
        mountain.registerExit(stairsDown);


        EnemyFlyweight wolf = new EnemyFlyweight("zuřivý vlk",5,3,5);
        EnemyFlyweight bat = new EnemyFlyweight(" vypasený netopýr",6,1,3);
        EnemyFlyweight skeleton = new EnemyFlyweight(" kostlivý horník",10,3,5);
        EnemyFlyweight zombie = new EnemyFlyweight(" zombie horník",15,5,6);
        EnemyFlyweight griffin = new EnemyFlyweight("majestátní gryf",50,5,20);
        Enemy enemy = Enemy.builder().enemy(wolf).type("normal").build();
        forrest.setEnemy(enemy);
        enemy = Enemy.builder().enemy(bat).type("normal").build();
        cave.setEnemy(enemy);
        enemy = Enemy.builder().enemy(zombie).type("normal").loot(new CommonItem("Klíč")).build();
        mine3.setEnemy(enemy);
        enemy = Enemy.builder().enemy(skeleton).type("normal").build();
        mine.setEnemy(enemy);
        enemy = Enemy.builder().enemy(skeleton).type("normal").loot(new Weapon("elfký luk", 10,20)).build();
        mine2.setEnemy(enemy);
        enemy = Enemy.builder().enemy(griffin).type("boss").loot(new CommonItem("vejce")).build();
        mountain.setEnemy(enemy);

        field.setItem(new Weapon("rezavý meč", 3,6));
        mine.setItem(new Weapon("krumpáč", 5,10));

        this.currentRoom = field;
    }

    /**
     *  Sets room, where the user currently resides
     */
    @Override
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    @Override
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     *  Sets finished flag, indicating game is done/finished
     */
    @Override
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    /**
     *  Retrieves finished flag -> parent components decides whether to end the game
     *  based on this method
     */
    @Override
    public boolean isFinished() {
        return finished;
    }
}
