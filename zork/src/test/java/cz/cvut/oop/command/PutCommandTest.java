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

public class PutCommandTest {

    GameData gameData;

    @Test
    public void execute_WithNoItemInInventory_ShouldReturnRightAnswer() {
        Command command = new PutCommand();
        gameData = new GameDataImpl();

        String[] arguments = new String[1];
        arguments[0] = "item";
        Assert.assertEquals("Nemáš žádný předmět", command.execute(arguments, gameData));
    }

    @Test
    public void execute_WithWrongArgument_ShouldReturnRightAnswer() {
        Command command = new PutCommand();
        gameData = new GameDataImpl();

        Room room = new RoomImpl("room", "test_room",  false);
        gameData.setCurrentRoom(room);

        gameData.getHero().getInventory().setData(new CommonItem("item"));

        String[] arguments = new String[1];
        arguments[0] = "kk";
        Assert.assertEquals("Nemáš daný předmět", command.execute(arguments, gameData));
    }

    @Test
    public void execute_WithNoArgument_ShouldReturnRightAnswer() {
        Command command = new PutCommand();
        gameData = new GameDataImpl();

        Room room = new RoomImpl("room", "test_room",  false);
        gameData.setCurrentRoom(room);

        gameData.getHero().getInventory().setData(new CommonItem("item"));

        String[] arguments = new String[1];
        arguments[0] = null;
        Assert.assertEquals("Nezadal jsi argumenty", command.execute(arguments, gameData));
    }

    @Test
    public void execute_WithRightArgument_ShouldReturnRightAnswer() {
        Command command = new PutCommand();
        gameData = new GameDataImpl();

        Room room = new RoomImpl("room", "test_room",  false);
        gameData.setCurrentRoom(room);

        gameData.getHero().getInventory().setData(new CommonItem("item"));

        String[] arguments = new String[1];
        arguments[0] = "item";
        Assert.assertEquals("Položil jsi předmět item", command.execute(arguments, gameData));
    }

    @Test
    public void execute_WithRightArgument_ShouldBeInRoom() {
        Command command = new PutCommand();
        gameData = new GameDataImpl();

        Room room = new RoomImpl("room", "test_room",  false);
        gameData.setCurrentRoom(room);

        gameData.getHero().getInventory().setData(new CommonItem("item"));

        String[] arguments = new String[1];
        arguments[0] = "item";
        command.execute(arguments, gameData);

        Assert.assertEquals("item", gameData.getCurrentRoom().getItem().getName());
    }
}
