package cz.cvut.oop.command;

import cz.cvut.oop.game.GameData;
import cz.cvut.oop.game.Room;
import cz.cvut.oop.game.item.Item;

public final class PutCommand implements Command{


    private Item item;

    public PutCommand(){

    }

    @Override
    public String getName() {
        return "polož";
    }

    @Override
    public String execute(String[] arguments, GameData gameData) {
        boolean wrong = true;
        if(!gameData.getHero().getInventory().getData().isEmpty()){

            for(Item i : gameData.getHero().getInventory().getData()){

               if (arguments[0] == null){
                    return "Nezadal jsi argumenty";
                }
                else if(arguments[0].replaceAll("\\s+","").equals(i.getName().replaceAll("\\s+",""))){
                    wrong = false;
                    this.item = i;
                    gameData.getCurrentRoom().setItem(i);


                }
            }
            if (wrong == true){
                return "Nemáš daný předmět";
            }
            gameData.getHero().getInventory().remove(this.item);
        }
        else{
            return "Nemáš žádný předmět";
        }
        return "Položil jsi předmět " + item.getName();
    }
}
