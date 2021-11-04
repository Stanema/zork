package cz.cvut.oop.command;

import cz.cvut.oop.game.GameData;
import cz.cvut.oop.game.item.Item;

public final class TakeCommand implements Command{


    private Item item;

    public TakeCommand(){

    }

    @Override
    public String getName() {
        return "seber";
    }

    @Override
    public String execute(String[] arguments, GameData gameData) {
        this.item = gameData.getCurrentRoom().getItem();
        if(this.item != null){
            gameData.getHero().getInventory().add(this.item);
            gameData.getCurrentRoom().setItem(null);
        }
        else{
         return "Není tu žádný předmět";
        }
        return "Sebral jsi předmět " + item.getName();
    }
}
