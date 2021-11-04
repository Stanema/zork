package cz.cvut.oop.command;

import cz.cvut.oop.game.GameData;
import cz.cvut.oop.game.Room;
import cz.cvut.oop.game.RoomImpl;

import java.util.*;

public final class MoveCommand implements Command{


    private Room currentRoom;
    private String nextRoomName;
    //private List<Room> exits;

    public MoveCommand(){

    }

    @Override
    public String getName() {
        return "jdi";
    }

    @Override
    public String execute(String[] arguments, GameData gameData) {

        Collection<Room> exits = gameData.getCurrentRoom().getExits();
        boolean wrong = true;
        if (arguments[0] == null){
            return "Nezadal jsi argumenty";
        }
        else if(!exits.isEmpty()){

            for (Room r : exits) {

                if (arguments[0].replaceAll("\\s+", "").equals(r.getName().replaceAll("\\s+", "")) && r.isLocked() == false) {
                    gameData.setCurrentRoom(r);
                    nextRoomName = r.getName();
                    wrong = false;
                }
                else if (arguments[0].replaceAll("\\s+", "").equals(r.getName().replaceAll("\\s+", "")) && r.isLocked() == true) {
                    wrong = false;
                    return "Narazil jsi na zamčené dveře, potřebuješ použít klíč";
                }

            }
            if (wrong == true){
                return "Zadaný špatný východ";
            }
        }
        else if(exits.size()==0){
            return "Neexistuje žádný východ";
        }


        return "Vydáváš se do " + gameData.getCurrentRoom().getName() + "\n" + gameData.getCurrentRoom().getDescriptionWithExits();
    }
}
