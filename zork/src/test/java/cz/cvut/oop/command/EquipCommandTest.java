package cz.cvut.oop.command;

import cz.cvut.oop.game.*;
import cz.cvut.oop.game.enemy.Enemy;
import cz.cvut.oop.game.enemy.EnemyFlyweight;
import cz.cvut.oop.game.item.Weapon;
import org.junit.Assert;
import org.junit.Test;

public class EquipCommandTest {

    GameData gameData;

    @Test
    public void execute_WithNoWeaponInRoom_ShouldReturnRightAnswer() {
        Command command = new EquipCommand();
        gameData = new GameDataImpl();

        Room room = new RoomImpl("room", "test_room",  false);


        gameData.setCurrentRoom(room);

        String[] arguments = new String[1];
        arguments[0] = " ";
        Assert.assertEquals("Není tu žádný předmět", command.execute(arguments, gameData));
    }

    @Test
    public void execute_WithWeaponInRoom_ShouldReturnRightAnswer() {
        Command command = new EquipCommand();
        gameData = new GameDataImpl();

        Room room = new RoomImpl("room", "test_room",  false);
        room.setItem(new Weapon("item", 5,10));
        gameData.setCurrentRoom(room);

        String[] arguments = new String[1];
        arguments[0] = " ";
        Assert.assertEquals("Vybavil jsi si předmět item který má poškození 5:10", command.execute(arguments, gameData));
    }

    @Test
    public void execute_WithWeaponInRoom_ShouldEquip() {
        Command command = new EquipCommand();
        gameData = new GameDataImpl();

        Room room = new RoomImpl("room", "test_room",  false);
        room.setItem(new Weapon("item", 5,10));
        gameData.setCurrentRoom(room);

        String[] arguments = new String[1];
        arguments[0] = " ";
        command.execute(arguments, gameData);

        Assert.assertEquals("item", gameData.getHero().getWeapon().getName());
    }

}
