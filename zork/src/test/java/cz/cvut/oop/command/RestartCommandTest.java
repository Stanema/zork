package cz.cvut.oop.command;

import cz.cvut.oop.game.GameData;
import cz.cvut.oop.game.GameDataImpl;
import cz.cvut.oop.game.Room;
import cz.cvut.oop.game.RoomImpl;
import org.junit.Assert;
import org.junit.Test;

public class RestartCommandTest {

    GameData gameData;

    @Test
    public void execute_ShouldReturnRightAnswer() {
        Command command = new RestartCommand();
        gameData = new GameDataImpl();

        String[] arguments = new String[1];
        arguments[0] = "";

        Assert.assertEquals("Proběhlo restartování hry\n"+ gameData.getCurrentRoom().getDescriptionWithExits(), command.execute(arguments, gameData));
    }

    @Test
    public void execute_ShouldRestartTheGame() {
        Command command = new RestartCommand();
        gameData = new GameDataImpl();

        gameData.getHero().setHealth(50);

        String[] arguments = new String[1];
        arguments[0] = "";
        command.execute(arguments, gameData);
        Assert.assertEquals(100, gameData.getHero().getHealth());
    }



}
