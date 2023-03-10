package kz.bootcamp4.springboot.bootcamp4.springboot.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bootcamp4.springboot.bootcamp4.springboot.db.Item;
import kz.bootcamp4.springboot.bootcamp4.springboot.model.ShopItem;
import kz.bootcamp4.springboot.bootcamp4.springboot.model.ShopMarket;
import kz.bootcamp4.springboot.bootcamp4.springboot.repository.ItemRepository;
import kz.bootcamp4.springboot.bootcamp4.springboot.repository.ManufacturerRepository;
import kz.bootcamp4.springboot.bootcamp4.springboot.repository.MarketRepository;
import kz.bootcamp4.springboot.bootcamp4.springboot.service.ItemService;
import kz.bootcamp4.springboot.bootcamp4.springboot.service.ManufacturerService;
import kz.bootcamp4.springboot.bootcamp4.springboot.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ItemRepository itemRepository; //мы подтянули свой repository

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private MarketRepository marketRepository;
    @Autowired
    private ItemService itemService;

    @Autowired
    private ManufacturerService manufacturerService;
    @Autowired
    private MarketService marketService;

    @GetMapping(value = "/")
    public String index(Model model) {

        model.addAttribute("tovary", itemService.getItems());
        model.addAttribute("total", itemService.sumOfPrices());
        return "indexPage";
    }

    @GetMapping(value = "/additem")
    public String addItem(Model model) {
        model.addAttribute("manufacturers", manufacturerService.getManufacturers());
        return "addItem";
    }

    @PostMapping(value = "/add-item-v3")
    public String addItemByObject(ShopItem item) {
        itemService.addItem(item);
        return "redirect:/additem?success";
    }

    @PostMapping(value = "/add-item-v2")
    public void test(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("item_name");
        double price = Double.parseDouble(request.getParameter("item_price"));
        int amount = Integer.parseInt(request.getParameter("item_amount"));

        Item item = Item.builder()
                .name(name)
                .price(price)
                .amount(amount)
                .build();
        //dbUtil.addItem(item);
        try {
            response.sendRedirect("/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/details/{id}/{link}.html")
    public String detailsView(@PathVariable(name = "id") Long id,
                              @PathVariable(name = "link") String link,
                              Model model) {
        ShopItem shopItem = itemService.getItem(id);
        model.addAttribute("tovar", shopItem);
        model.addAttribute("manufacturers", manufacturerService.getManufacturers());
        model.addAttribute("markets", marketService.getAvailableMarkets(shopItem));
        return "details";
    }

    @PostMapping(value = "/update-item")
    public String updateItem(ShopItem item) {
        item = itemService.updateItem(item);
        if (item != null) {
            return "redirect:/details/" + item.getId() + "/" + item.getLink() + ".html";
        }
        return "redirect:/";
    }

    @PostMapping(value = "/delete-item")
    public String deleteItem(@RequestParam(name = "id") Long id) {
        itemService.deleteItem(id);
        return "redirect:/";
    }

    @GetMapping(value = "/search")
    public String search(
            @RequestParam(name = "key", required = false, defaultValue = "") String key, //defaultValue - требует String, requestParam конвертирует в нужный параметр
            @RequestParam(name = "from_price", required = false, defaultValue = "0") double fromPrice, //required и defaultValue - подстраховка, чтобы нам не выходила ошибка когда будем отправлять пустой запрос
            @RequestParam(name = "to_price", required = false, defaultValue = Double.MAX_VALUE + "") double toPrice,
            @RequestParam(name = "from_amount", required = false, defaultValue = "0") int fromAmount,
            @RequestParam(name = "to_amount", required = false, defaultValue = Integer.MAX_VALUE + "") int toAmount,
            @RequestParam(name = "manufacturer_id", required = false, defaultValue = "0") Long manufacturerId,
            Model model
    ) {
        if (manufacturerId != null && manufacturerId != 0L) {
            List<ShopItem> items =
                    itemRepository.poiskWithManufacturer(
                            "%" + key.toLowerCase() + "%",
                            fromPrice,
                            toPrice,
                            fromAmount,
                            toAmount,
                            manufacturerId
                    );
            model.addAttribute("tovary", items);
        } else {
            List<ShopItem> items =
                    itemRepository.poisk(
                            "%" + key.toLowerCase() + "%",
                            fromPrice,
                            toPrice,
                            fromAmount,
                            toAmount
                    );
            model.addAttribute("tovary", items);
        }

        model.addAttribute("manufacturers", manufacturerRepository.findAll());
        return "search";
    }

    @PostMapping(value = "/assign-market")
    //у таблицы ManyToMany нет сущности - это таблица которая связывает 2 сущностей, поэтому передаем данные вот так
    public String assignMarket(@RequestParam(name = "market_id") Long marketId,
                               @RequestParam(name = "item_id") Long itemId) {

        ShopMarket market = marketRepository.findById(marketId).orElseThrow();
        ShopItem item = itemRepository.findById(itemId).orElseThrow();
        if (!item.getMarkets().contains(market)) { //чтобы маркет не дублировался
            item.getMarkets().add(market);
            itemRepository.save(item);
        }
        return "redirect:/details/" + item.getId() + "/" + item.getLink() + ".html";
    }

    @PostMapping(value = "/remove-market")
    public String removeMarket(@RequestParam(name = "market_id") Long marketId,
                               @RequestParam(name = "item_id") Long itemId) {

        ShopMarket market = marketRepository.findById(marketId).orElseThrow();
        ShopItem item = itemRepository.findById(itemId).orElseThrow();

        item.getMarkets().remove(market); //ты вытащил объект, оттуда вытащил пульку и обратно положил
        itemRepository.save(item); //и сохранил
        return "redirect:/details/" + item.getId() + "/" + item.getLink() + ".html";
    }
}
