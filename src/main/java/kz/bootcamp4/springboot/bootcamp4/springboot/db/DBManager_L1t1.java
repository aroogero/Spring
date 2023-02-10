package kz.bootcamp4.springboot.bootcamp4.springboot.db;

import java.util.ArrayList;

public class DBManager_L1t1 {
    private static final ArrayList<Item_L1t1> items = new ArrayList<>();

    static {
       /* Item_L1t1 i7 = new Item_L1t1(6L,"Mac Book Pro","8 GB RAM 255 GB SSD Intel Core i7",1199.99);
        items.add(i7);*/
        items.add(new Item_L1t1(1L,"Mac Book Pro","8 GB RAM 255 GB SSD Intel Core i7",1199.99));
        items.add(new Item_L1t1(2L,"Mac Book Pro","16 GB RAM 512 GB SSD Apple M1",1499.99));
        items.add(new Item_L1t1(3L,"Mac Book Pro","8 GB RAM 1 TB SSD Apple M1",1799.99));
        items.add(new Item_L1t1(4L,"Mac Book Pro","16 GB RAM 512 GB SSD Intel Core i7",1299.99));
        items.add(new Item_L1t1(5L,"Mac Book Pro","8 GB RAM 512 GB SSD Intel Core i5",999.99));
        items.add(new Item_L1t1(6L,"Mac Book Pro","32 GB RAM 512 GB SSD Intel Core i7",1399.99));

    }

    public static ArrayList<Item_L1t1> getItems() {
        return items;
    }
}
