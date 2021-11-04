package cz.cvut.oop.command;


import cz.cvut.oop.game.*;
import org.junit.Assert;
import org.junit.Test;

public class EndCommandTest {

    GameData gameData;

    @Test
    public void execute_ShouldReturnRightAnswer() {
        Command command = new EndCommand();
        gameData = new GameDataImpl();

        String[] arguments = new String[1];
        arguments[0] = "";

        Assert.assertEquals("Ukončil jsi hru", command.execute(arguments, gameData));
    }

    @Test
    public void execute_ShouldTerminateTheGame() {
        Command command = new EndCommand();
        gameData = new GameDataImpl();

        String[] arguments = new String[1];
        arguments[0] = "";
        command.execute(arguments, gameData);

        Assert.assertEquals(true, gameData.isFinished());
    }


}
