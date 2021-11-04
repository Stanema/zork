package cz.cvut.oop.main;

import cz.cvut.oop.game.GameImpl;
import cz.cvut.oop.game.hero.Hero;
import cz.cvut.oop.game.inventory.Inventory;
import cz.cvut.oop.game.item.CommonItem;
import cz.cvut.oop.game.item.Weapon;
import cz.cvut.oop.ui.CommandLineUi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger log = LogManager.getLogger(CommandLineUi.class);

    public static void main(String[] args){
        //new CommandLineUi(new GameImpl()).start();
        GameImpl.registerCommands();
        CommandLineUi cmdlnUI = CommandLineUi.getInstance(new GameImpl());
        cmdlnUI.start();
    }



}
