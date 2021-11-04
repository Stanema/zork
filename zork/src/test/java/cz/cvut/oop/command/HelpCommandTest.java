package cz.cvut.oop.command;;

import cz.cvut.oop.game.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HelpCommandTest {


    private Map<String, Command> commands = new HashMap<>();
    GameData gameData = new GameDataImpl();


    @Test
    public void execute_ShouldReturnRightAnswer() {
        Command help = new HelpCommand(commands);
        commands.put(help.getName(), help);

        Room room = new RoomImpl("room", "test_room",  false);
        gameData.setCurrentRoom(room);

        String[] arguments = new String[1];
        arguments[0] = "";

        Assert.assertEquals("Možné příkazy: [nápověda]", help.execute(arguments, gameData));
    }


}