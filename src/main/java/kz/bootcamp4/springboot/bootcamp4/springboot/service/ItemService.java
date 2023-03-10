package kz.bootcamp4.springboot.bootcamp4.springboot.service;

import kz.bootcamp4.springboot.bootcamp4.springboot.model.ShopItem;

import java.util.List;

public interface ItemService {

    List<ShopItem> getItems();
    double sumOfPrices();
}
