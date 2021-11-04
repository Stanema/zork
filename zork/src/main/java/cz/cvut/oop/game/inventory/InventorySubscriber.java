package cz.cvut.oop.game.inventory;

import cz.cvut.oop.game.item.Item;

public interface InventorySubscriber {

    void notifyChanged(Item item);
}
