package cz.cvut.oop.command;;

import cz.cvut.oop.game.*;
import cz.cvut.oop.game.enemy.Enemy;
import cz.cvut.oop.game.enemy.EnemyFlyweight;
import cz.cvut.oop.game.item.Item;
import cz.cvut.oop.game.item.Weapon;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class AttackCommandTest {

    GameData gameData;

    @Test
    public void execute_WithoutWeaponEquiped_ShouldReturnRightAnswer() {
        Command command = new AttackCommand();
        gameData = new GameDataImpl();

        Room room = new RoomImpl("room", "test_room",  false);
        Enemy enemy = new Enemy(new EnemyFlyweight("enemy",15,3,5), "normal", new Weapon("item",1,1));

        gameData.setCurrentRoom(room);
        gameData.getCurrentRoom().setEnemy(enemy);

        String[] arguments = new String[1];
        arguments[0] = " ";
        Assert.assertEquals("Nemáš zbraň a enemy tě zranil", command.execute(arguments, gameData));
    }

    @Test
    public void execute_WithoutEnemyInRoom_ShouldReturnRightAnswer() {
        Command command = new AttackCommand();
        gameData = new GameDataImpl();

        Room room = new RoomImpl("room", "test_room",  false);
        gameData.setCurrentRoom(room);

        String[] arguments = new String[1];
        arguments[0] = " ";
        Assert.assertEquals("Není na koho zaútočit a tak jsi jen epicky zamával zbraní", command.execute(arguments, gameData));
    }

    @Test
    public void execute_AttackEnemy_ShouldReturnRightAnswer() {
        Command command = new AttackCommand();
        gameData = new GameDataImpl();

        Room room = new RoomImpl("room", "test_room",  false);
        Enemy enemy = new Enemy(new EnemyFlyweight("enemy",15,3,5), "normal", new Weapon("item",1,1));

        gameData.setCurrentRoom(room);
        gameData.getCurrentRoom().setEnemy(enemy);
        gameData.getHero().setWeapon(new Weapon("test",10,10));

        String[] arguments = new String[1];
        arguments[0] = " ";
        Assert.assertEquals("Zasáhl jsi nepřítele za " + 10 + " poškození a zbývá mu ještě " + 5 + " životů", command.execute(arguments, gameData));
    }

    @Test
    public void execute_KillEnemyWithNoLoot_ShouldReturnRightAnswer() {
        Command command = new AttackCommand();
        gameData = new GameDataImpl();

        Room room = new RoomImpl("room", "test_room",  false);
        Enemy enemy = new Enemy(new EnemyFlyweight("enemy",10,3,5), "normal", null);

        gameData.setCurrentRoom(room);
        gameData.getCurrentRoom().setEnemy(enemy);
        gameData.getHero().setWeapon(new Weapon("test",10,10));

        String[] arguments = new String[1];
        arguments[0] = " ";
        Assert.assertEquals("Zasáhl jsi nepřítele za " + 10 +" poškození, nepřítel je mrtvý", command.execute(arguments, gameData));
    }

    @Test
    public void execute_KillEnemyWithLoot_ShouldReturnRightAnswer() {
        Command command = new AttackCommand();
        gameData = new GameDataImpl();

        Room room = new RoomImpl("room", "test_room",  false);
        Enemy enemy = new Enemy(new EnemyFlyweight("enemy",10,3,5), "normal", new Weapon("item",1,1));

        gameData.setCurrentRoom(room);
        gameData.getCurrentRoom().setEnemy(enemy);
        gameData.getHero().setWeapon(new Weapon("test",10,10));

        String[] arguments = new String[1];
        arguments[0] = " ";
        Assert.assertEquals("Zasáhl jsi nepřítele za " + 10 +" poškození, nepřítel je mrtvý a v jeho útrobách jsi našel: item" , command.execute(arguments, gameData));
    }

}
