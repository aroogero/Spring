package kz.bootcamp4.springboot.bootcamp4.springboot.service.impl;

import kz.bootcamp4.springboot.bootcamp4.springboot.model.ShopItem;
import kz.bootcamp4.springboot.bootcamp4.springboot.repository.ItemRepository;
import kz.bootcamp4.springboot.bootcamp4.springboot.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<ShopItem> getItems() {

        return itemRepository.findAll();
    }

    @Override
    public double sumOfPrices() {
        return itemRepository.sumOfPrices();
    }

    @Override
    public ShopItem addItem(ShopItem item) {
        item.setLink(item.getName().toLowerCase().replace(' ', '-'));
        return itemRepository.save(item);
    }

    @Override
    public ShopItem getItem(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public ShopItem updateItem(ShopItem item) {
        ShopItem oldItem = itemRepository.findById(item.getId()).orElse(null); //если в форме мы ничего не передаем, значение будет пустым, поэтому id получаем отдельно
        if (oldItem != null) {
            oldItem.setName(item.getName());
            oldItem.setAmount(item.getAmount());
            oldItem.setPrice(item.getPrice());
            oldItem.setDescription(item.getDescription());
            oldItem.setLink(item.getName().toLowerCase().replace(' ', '-'));
            oldItem.setManufacturer(item.getManufacturer());
            oldItem = itemRepository.save(oldItem);
        }
        return oldItem;
    }

    @Override
    public void deleteItem(Long id) {
        ShopItem item = getItem(id);
        itemRepository.delete(item); //Удаляем по объекту. если через айди будем удалять, то выйдет ошибка если туда попадет несуществующий айди
    }

    @Override
    public List<ShopItem> search(String key, double fromPrice, double toPrice, int fromAmount, int toAmount, Long manufacturerId) {
        List<ShopItem> items;
        if (manufacturerId != null && manufacturerId != 0L) {
            items =
                    itemRepository.poiskWithManufacturer(
                            "%" + key.toLowerCase() + "%",
                            fromPrice,
                            toPrice,
                            fromAmount,
                            toAmount,
                            manufacturerId
                    );
        } else {
            items =
                    itemRepository.poisk(
                            "%" + key.toLowerCase() + "%",
                            fromPrice,
                            toPrice,
                            fromAmount,
                            toAmount
                    );
        }
        return items;
    }
}
