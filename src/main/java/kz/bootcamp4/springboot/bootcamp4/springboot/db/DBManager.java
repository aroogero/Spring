package kz.bootcamp4.springboot.bootcamp4.springboot.db;

import java.util.ArrayList;

public class DBManager {
    private static final ArrayList<Item> items = new ArrayList<>();  //Можем сделать final - потому что заново переопредeлять не будем
    private static Long id = 5L;

    static {
        items.add(Item.builder()
                .id(1L)
                .name("Iphone 13")
                .price(500000)
                .amount(30)
                .link("iphone-13")
                .build());
        items.add(Item.builder()
                .id(2L)
                .name("Iphone 14")
                .price(400000)
                .amount(40)
                .link("iphone-14")
                .build());
        items.add(Item.builder()
                .id(3L)
                .name("Iphone 12")
                .price(300000)
                .link("iphone-12")
                .amount(20)
                .build());
        items.add(Item.builder()
                .id(4L)
                .name("Iphone 11")
                .link("iphone-11")
                .price(200000)
                .amount(10)
                .build());
    }
    public static void addItem(Item item) {
        item.setId(id);
        items.add(item);
        String link = item.getName().toLowerCase(); //опускает заглавные буквы в нижний регистр
        link = link.replace(' ', '-').replace('.','-'); //перезаписываем и все пробелы и точки заменяем на -
        item.setLink(link); //и сохраняем
        id++;
    }

    public static ArrayList<Item> getItems() {
        return items;
    }
    public static Item getItem(Long id) {
        return items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
