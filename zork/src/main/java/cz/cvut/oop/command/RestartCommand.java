package cz.cvut.oop.command;

import cz.cvut.oop.game.GameData;
import cz.cvut.oop.game.GameDataImpl;

import java.util.Map;

public final class RestartCommand implements Command{



    public RestartCommand(){

    }

    @Override
    public String getName() {
        return "restart";
    }

    @Override
    public String execute(String[] arguments, GameData gameData) {
        gameData.set();
        return "Proběhlo restartování hry\n"+ gameData.getCurrentRoom().getDescriptionWithExits();
    }
}
