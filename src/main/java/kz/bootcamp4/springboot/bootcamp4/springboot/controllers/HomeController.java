package kz.bootcamp4.springboot.bootcamp4.springboot.controllers;

import kz.bootcamp4.springboot.bootcamp4.springboot.db.DBManager;
import kz.bootcamp4.springboot.bootcamp4.springboot.db.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class HomeController {
    @GetMapping(value = "/")
    public String index(Model model) {

        ArrayList<Item> items = DBManager.getItems();
        model.addAttribute("tovary", items);
        Item best = new Item(777l,"Iphone 20", 1, 5000000);
        model.addAttribute("superTovar", best);

        return "indexPage";
    }
    @GetMapping(value = "/about")
    public String about() {
        return "about";
    }
    @PostMapping(value="/add-item")
    public String addItem(@RequestParam(name="item_name") String name,
    @RequestParam(name="item_price") double price,
    @RequestParam(name="item_amount") int amount){
        Item item = new Item();
        item.setName(name);
        item.setPrice(price);
        item.setAmount(amount);
        DBManager.addItem(item);
        return "redirect:/";
    }
}
