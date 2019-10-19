package lList;

import building.Space;

public class Item {
    public Space data;
    public Item next;


    public Item(Space data) {
        this.data = data;
        next = null;
    }

    public Item(Space data, Item next) {
        this.data = data;
        this.next = next;
    }

}
