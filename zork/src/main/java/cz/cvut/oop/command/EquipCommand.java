package cz.cvut.oop.command;

import cz.cvut.oop.game.GameData;
import cz.cvut.oop.game.item.Item;
import cz.cvut.oop.game.item.Weapon;

public final class EquipCommand implements Command{


    private Item item;

    public EquipCommand(){

    }

    @Override
    public String getName() {
        return "nasaď";
    }

    @Override
    public String execute(String[] arguments, GameData gameData) {
        this.item = gameData.getCurrentRoom().getItem();
        if(gameData.getCurrentRoom().getItem() == null){
            return "Není tu žádný předmět";
        }
        else if(gameData.getHero().getWeapon() == null){
            gameData.getHero().setWeapon(this.item);
            gameData.getCurrentRoom().setItem(null);
        }
        else{
            gameData.getCurrentRoom().setItem(gameData.getHero().getWeapon());
            gameData.getHero().setWeapon(this.item);
        }
        return "Vybavil jsi si předmět " + item.getName() + " který má poškození " + item.statsMin() + ":"+item.statsMax();
    }
}
