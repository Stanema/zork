package cz.cvut.oop.game.inventory;

import cz.cvut.oop.game.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> data = new ArrayList<>();
    private List<InventorySubscriber> subscribers = new ArrayList<>();

    public void add(Item item){
        data.add(item);
        this.notifySubscribers(item);
    }

    public void notifySubscribers(Item data){
        subscribers.forEach(myListSubscriber -> myListSubscriber.notifyChanged(data));
    }

    public void subscribe(InventorySubscriber inventorySubscriber){
        subscribers.add(inventorySubscriber);
    }

    public List<Item> getData() {
        return data;
    }

    public void setData(Item data) {
        this.data.add(data);
    }

    public void remove(Item item) {
            this.data.remove(item);
            this.notifySubscribers(item);
    }
}
