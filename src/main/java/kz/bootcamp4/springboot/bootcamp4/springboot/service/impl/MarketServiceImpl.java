package kz.bootcamp4.springboot.bootcamp4.springboot.service.impl;

import kz.bootcamp4.springboot.bootcamp4.springboot.model.ShopItem;
import kz.bootcamp4.springboot.bootcamp4.springboot.model.ShopMarket;
import kz.bootcamp4.springboot.bootcamp4.springboot.repository.MarketRepository;
import kz.bootcamp4.springboot.bootcamp4.springboot.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketServiceImpl implements MarketService {
    @Autowired
    private MarketRepository marketRepository;

    @Override
    public List<ShopMarket> getMarkets() {
        return marketRepository.findAll();
    }
    @Override
    public List<ShopMarket> getAvailableMarkets(ShopItem item) {
        List<ShopMarket> markets = getMarkets();
        if (item != null) {
            markets.removeAll(item.getMarkets());
        }
        return markets;
    }
}
