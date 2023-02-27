package kz.bootcamp4.springboot.bootcamp4.springboot.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bootcamp4.springboot.bootcamp4.springboot.db.Item;
import kz.bootcamp4.springboot.bootcamp4.springboot.model.ShopItem;
import kz.bootcamp4.springboot.bootcamp4.springboot.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ItemRepository itemRepository; //мы подтянули свой repository

    @GetMapping(value = "/")
    public String index(Model model) {

        List<ShopItem> items = itemRepository.findAll();//List - это интерфейс ArrayList-а, более абстрактная версия
         model.addAttribute("tovary", items);
        return "indexPage";
    }

    @GetMapping(value="/additem")
    public String addItem(Model model) {
        return "addItem";
    }
    @PostMapping(value="/add-item-v3")
    public String addItemByObject(ShopItem item){
        item.setLink(item.getName().toLowerCase().replace(' ', '-'));
        itemRepository.save(item);
        return "redirect:/additem?success";
    }
    @PostMapping(value="/add-item-v2")
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
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }
    @GetMapping(value="/details/{id}/{link}.html")
    public String detailsView(@PathVariable(name="id") Long id,
                              @PathVariable(name="link") String link, //Этот link в этом случае никакую роль не играет. Мы все равно по id будем считывать
                              Model model) {
        ShopItem shopItem = itemRepository.findById(id).get();
      model.addAttribute("tovar", shopItem);
        return "details";
    }

    @PostMapping(value="/update-item")
    public String updateItem(ShopItem item) {
        ShopItem oldItem = itemRepository.findById(item.getId()).orElse(null); //если в форме мы ничего не передаем, значение будет пустым, поэтому id получаем отдельно
        if(oldItem!=null) {
            oldItem.setName(item.getName());
            oldItem.setAmount(item.getAmount());
            oldItem.setPrice(item.getPrice());
            oldItem.setDescription(item.getDescription());
            oldItem.setLink(item.getName().toLowerCase().replace(' ', '-'));
            itemRepository.save(oldItem);
            return "redirect:/details/" + item.getId() + "/" + item.getLink() + ".html";
        }
        return "redirect:/";
    }
}
