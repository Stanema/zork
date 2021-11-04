package cz.cvut.oop.game.hero;

import cz.cvut.oop.game.inventory.Inventory;
import cz.cvut.oop.game.inventory.InventorySubscriber;
import cz.cvut.oop.game.item.Item;

public class Hero  implements InventorySubscriber {
    private String name;
    private Item weapon;
    private int health;
    private Inventory inventory;

    public Hero(int health) {
        this.health = health;
        this.inventoryCapacity = 2;
        inventory = new Inventory();
        inventory.subscribe(this);


    }
    private int iterInt = 0;
    private int attackDamage;
    private Item armor;
    private int inventoryCapacity;

    public Inventory getInventory() {
        return inventory;
    }

    public Item getWeapon() {
        return weapon;
    }

    public void setWeapon(Item weapon) {
        this.weapon = weapon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Item getArmor() {
        return armor;
    }

    public void setArmor(Item armor) {
        this.armor = armor;
    }

    public int getInventoryCapacity() {
        return inventoryCapacity;
    }

    public void setInventoryCapacity(int inventoryCapacity) {
        this.inventoryCapacity = inventoryCapacity;
    }



    public void notifyChanged(Item item) {
        System.out.print("Inventář: ");
        for(Item i :inventory.getData())
            System.out.print(i.getName() + ", ");
        System.out.println();
    }
}
