package cz.cvut.oop.game;

import cz.cvut.oop.game.enemy.Enemy;
import cz.cvut.oop.game.enemy.EnemyFlyweight;
import cz.cvut.oop.game.item.Item;

import java.util.*;

/**
 *  Class represents Room, e.g. space in our game. It contains exits and can form a map of Rooms
 */
public class RoomImpl implements Room {

    private String name;
    private String description;
    private Map<String,Room> exits = new HashMap<>();
    private Enemy enemy;
    private Item loot;
    private  boolean isLocked;



    public RoomImpl(String name, String description, boolean isLocked){
        this.name = name;
        this.description = description;
        this.isLocked = isLocked;

    }

    @Override
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void setItem(Item item) {

        if(item == null){
            this.loot = null;
        }
        else if(item.getName().equals("Klíč")){
            this.loot = item;
            for(Map.Entry<String, Room> exit : exits.entrySet()){
                exit.getValue().unlock();
            }
        }
        else {
            this.loot = item;
        }
    }


    /**
     *  Adds new exit to map
     */
    @Override
    public void registerExit(Room room){
        exits.put(room.getName(), room);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Enemy getEnemy() {
        return this.enemy;
    }

    @Override
    public Item getItem() {
        return this.loot;
    }

    @Override
    public boolean isLocked() {
        return this.isLocked;
    }

    @Override
    public void unlock() {
        this.isLocked = false;
    }

    /**
     *  Method returns description of this room (from getDescription call)
     *  and should add possible exit names
     */
    @Override
    public String getDescriptionWithExits() {
        //TODO implementovat
        Collection<Room> exit = this.getExits();
        String s = "";
        for (Room room : exit)
           s += room.getName() +", ";

        if(this.enemy != null && this.loot != null){
            return description + "\n Východy: " + s + "\nPřed tebou stojí: " + enemy.getEnemyFlyweight().getName() + "\nNa zemi se válí: " + loot.getName();
        }
        else if (this.enemy != null && this.loot == null){
            return description + "\n Východy: " + s + "\nPřed tebou stojí: " + enemy.getEnemyFlyweight().getName();
        }
        else if(this.enemy == null && this.loot != null){
            return description + "\n Východy: " + s + "\nNa zemi se válí: " + loot.getName();
        }
        else{
            return description + "\n Východy: " + s;
        }
    }

    /**
     *  Method returns description of this room
     */
    @Override
    public String getDescription() {
        if(this.enemy == null){
            return description;
        }
        else{
            return description + "\nPřed tebou stojí: " + enemy.getEnemyFlyweight().getName();
        }
    }

    /**
     *  Return unmodifiable view of our map
     */
    @Override
    public Collection<Room> getExits() {
        return Collections.unmodifiableCollection(exits.values());
    }

    /**
     *  Returns room based on entered room (exit) name
     */
    @Override
    public Room getExitByName(String name) {
        return exits.getOrDefault(name, null);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomImpl room = (RoomImpl) o;
        return Objects.equals(name, room.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}

