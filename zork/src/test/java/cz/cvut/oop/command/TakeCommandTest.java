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

public class TakeCommandTest {

    GameData gameData;

    @Test
    public void execute_WithNoItemInRoom_ShouldReturnRightAnswer() {
        Command command = new TakeCommand();
        gameData = new GameDataImpl();

        Room room = new RoomImpl("room", "test_room",  false);
        gameData.setCurrentRoom(room);

        String[] arguments = new String[1];
        arguments[0] = " ";
        Assert.assertEquals("Není tu žádný předmět", command.execute(arguments, gameData));
    }

    @Test
    public void execute_WithItemInRoom_ShouldReturnRightAnswer() {
        Command command = new TakeCommand();
        gameData = new GameDataImpl();

        Room room = new RoomImpl("room", "test_room",  false);
        room.setItem(new CommonItem("item"));
        gameData.setCurrentRoom(room);

        String[] arguments = new String[1];
        arguments[0] = " ";
        Assert.assertEquals("Sebral jsi předmět item", command.execute(arguments, gameData));
    }

    @Test
    public void execute_WithItemInRoom_ShouldBeInInventory() {
        Command command = new TakeCommand();
        gameData = new GameDataImpl();

        Room room = new RoomImpl("room", "test_room",  false);
        room.setItem(new CommonItem("item"));
        gameData.setCurrentRoom(room);

        String[] arguments = new String[1];
        arguments[0] = " ";
        command.execute(arguments, gameData);

        Assert.assertEquals("item", gameData.getHero().getInventory().getData().get(0).getName());
    }

}
