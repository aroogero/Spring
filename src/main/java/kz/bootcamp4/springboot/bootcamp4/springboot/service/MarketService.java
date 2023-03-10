package kz.bootcamp4.springboot.bootcamp4.springboot.service;

import kz.bootcamp4.springboot.bootcamp4.springboot.model.ShopItem;
import kz.bootcamp4.springboot.bootcamp4.springboot.model.ShopMarket;

import java.util.List;

public interface MarketService {
    List<ShopMarket> getMarkets();
    List<ShopMarket> getAvailableMarkets(ShopItem item ); //дать всевозможные markets

}
