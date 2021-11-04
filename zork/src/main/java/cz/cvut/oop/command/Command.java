package cz.cvut.oop.command;

import cz.cvut.oop.game.GameData;

/**
 *  Represents Command interface containing method to indentify and execute command
 */
public interface Command {

    String getName();
    String execute(String[] arguments, GameData gameData);


}
