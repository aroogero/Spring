package kz.bootcamp4.springboot.bootcamp4.springboot.service;

import kz.bootcamp4.springboot.bootcamp4.springboot.model.ShopItem;

import java.util.List;

public interface ItemService {

    List<ShopItem> getItems();

    double sumOfPrices();

    ShopItem addItem(ShopItem item);

    ShopItem getItem(Long id);

    ShopItem updateItem(ShopItem item);

    void deleteItem(Long id);

    List<ShopItem> search(String key, double fromPrice, double toPrice, int fromAmount, int toAmount, Long manufacturerId);
    ShopItem assignMarket(Long marketId, Long itemId);
}
