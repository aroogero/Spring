package kz.bootcamp4.springboot.bootcamp4.springboot.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bootcamp4.springboot.bootcamp4.springboot.beans.DBConnector;
import kz.bootcamp4.springboot.bootcamp4.springboot.beans.FirstBean;
import kz.bootcamp4.springboot.bootcamp4.springboot.db.DBManager;
import kz.bootcamp4.springboot.bootcamp4.springboot.db.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class HomeController {

    @Autowired
    private FirstBean firstBean;

    @Value("${bitlab.name}")
    private String siteName;

    @Autowired
    private DBConnector dbConnector;

    @GetMapping(value = "/")
    public String index(Model model) {
        System.out.println(firstBean.getData());
        System.out.println(siteName);
        ArrayList<Item> items = dbConnector.getItems();
        model.addAttribute("tovary", items);

        Item best = Item.builder()
                .name("Iphone 20")
                .price(5000000)
                .amount(1)
                .build();

        model.addAttribute("superTovar", best);

        return "indexPage";
    }
    @GetMapping(value = "/changeData")
    public String changeData() {
       firstBean.setName("Almas"); //Внимание! Внимание! Внимание! Как у нас объект называется? Строка 22
        firstBean.setValue(44);
        return "redirect:/";
    }
    @GetMapping(value="/refreshData")
    public String refreshData() {
        firstBean.setName("Iliyas");
        firstBean.setValue(7);
        return "redirect:/";
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

    @GetMapping(value="/additem")
    public String addItem(Model model) {
        return "addItem";
    }
    @PostMapping(value="/add-item-v3")
    public String addItemByObject(Item item){ //здесь можно считывать не по RequestParam-ам, а целиком указать Item item
        DBManager.addItem(item);
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
        DBManager.addItem(item);
        try {
            response.sendRedirect("/");
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }
    @GetMapping(value="/details")
    public String details(@RequestParam(name="id") Long id, Model model) {
        model.addAttribute("tovar", DBManager.getItem(id));
        return "details";
    }
    @GetMapping(value="/details/{id}/{link}.html")
    public String detailsView(@PathVariable(name="id") Long id,
                              @PathVariable(name="link") String link, //Этот link в этом случае никакую роль не играет. Мы все равно по id будем считывать
                              Model model) {
        model.addAttribute("tovar", DBManager.getItem(id));
        return "details";
    }
}
