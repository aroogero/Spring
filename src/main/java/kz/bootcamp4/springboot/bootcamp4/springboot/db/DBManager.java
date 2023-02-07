package kz.bootcamp4.springboot.bootcamp4.springboot.db;

import java.util.ArrayList;

public class DBManager {
    private static final ArrayList<Item> items = new ArrayList<>();  //Можем сделать final - потому что заново переопредeлять не будем
    private static Long id = 5L;

    static {
        items.add(new Item(1L, "Iphone 13", 20, 550000));
        items.add(new Item(2L, "Iphone 14", 30, 650000));
        items.add(new Item(3L, "Iphone 12", 40, 450000));
        items.add(new Item(4L, "Iphone 11", 50, 350000));
    }
    public static void addItem(Item item) {
        item.setId(id);
        items.add(item);
        id++;
    }

    public static ArrayList<Item> getItems() {
        return items;
    }
}
