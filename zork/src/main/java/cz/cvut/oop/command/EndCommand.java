package cz.cvut.oop.command;

import cz.cvut.oop.game.GameData;

import java.util.Map;

public final class EndCommand implements Command{


    public EndCommand(){



    }

    @Override
    public String getName() {
        return "konec";
    }

    @Override
    public String execute(String[] arguments, GameData gameData) {
        gameData.setFinished(true);
        return "Ukončil jsi hru";
    }
}
