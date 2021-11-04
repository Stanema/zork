package cz.cvut.oop.command;


import cz.cvut.oop.game.*;
import cz.cvut.oop.game.GameData;
import cz.cvut.oop.game.GameDataImpl;
import cz.cvut.oop.game.Room;
import cz.cvut.oop.game.RoomImpl;
import cz.cvut.oop.game.item.CommonItem;
import cz.cvut.oop.game.item.Weapon;
import org.junit.Assert;
import org.junit.Test;

public class MoveCommandTest {
    GameData gameData;

    @Test
    public void execute_WithNoExit_ShouldReturnRightAnswer() {
        Command command = new MoveCommand();
        gameData = new GameDataImpl();

        Room room = new RoomImpl("room", "test_room",  false);
        gameData.setCurrentRoom(room);

        String[] arguments = new String[1];
        arguments[0] = "room";
        Assert.assertEquals("Neexistuje žádný východ", command.execute(arguments, gameData));
    }

    @Test
    public void execute_WithRightArguments_ShouldReturnRightAnswer() {
        Command command = new MoveCommand();
        gameData = new GameDataImpl();

        Room room = new RoomImpl("room", "test_room",  false);
        gameData.setCurrentRoom(room);
        gameData.getCurrentRoom().registerExit(room);

        String[] arguments = new String[1];
        arguments[0] = "room";
        Assert.assertEquals("Vydáváš se do " + gameData.getCurrentRoom().getName() + "\n" + gameData.getCurrentRoom().getDescriptionWithExits(), command.execute(arguments, gameData));
    }

    @Test
    public void execute_WithWrongArguments_ShouldReturnRightAnswer() {
        Command command = new MoveCommand();
        gameData = new GameDataImpl();

        Room room = new RoomImpl("room", "test_room",  false);
        gameData.setCurrentRoom(room);
        gameData.getCurrentRoom().registerExit(room);

        String[] arguments = new String[1];
        arguments[0] = "moor";
        Assert.assertEquals("Zadaný špatný východ", command.execute(arguments, gameData));
    }

    @Test
    public void execute_WithLockedExit_ShouldReturnRightAnswer() {
        Command command = new MoveCommand();
        gameData = new GameDataImpl();

        Room room = new RoomImpl("room", "test_room",  true);
        gameData.setCurrentRoom(room);
        gameData.getCurrentRoom().registerExit(room);

        String[] arguments = new String[1];
        arguments[0] = "room";
        Assert.assertEquals("Narazil jsi na zamčené dveře, potřebuješ najít klíč", command.execute(arguments, gameData));
    }

}

