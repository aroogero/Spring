package kz.bootcamp4.springboot.bootcamp4.springboot.controllers;

import kz.bootcamp4.springboot.bootcamp4.springboot.db.DBManager_L1t1;
import kz.bootcamp4.springboot.bootcamp4.springboot.db.Item_L1t1;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
@Controller
public class HomeController_L1t1 {
    @GetMapping(value = "/homePage_L1T1")
    public String methodName(Model model) {
        ArrayList<Item_L1t1> items = DBManager_L1t1.getItems();
        model.addAttribute("tovary1", items);
        return "homePage_L1t1";
    }

    @GetMapping(value="/openPage")
    public String openPage() {
        return "addItem_L1t2";
    }

    @PostMapping(value="/addItem_L1t2")
    public String addItem2(@RequestParam(name="itemName") String name,
    @RequestParam(name="itemDescription") String description,
                           @RequestParam(name="itemPrice") double price) {
        Item_L1t1 newObject = new Item_L1t1();
        newObject.setName(name);
        newObject.setDescription(description);
        newObject.setPrice(price);
        DBManager_L1t1.addItem(newObject);
        return "redirect:/homePage_L1T1";
    }
    @GetMapping(value="/details_L1tasktwo")
    public String details(@RequestParam (name = "id") Long id, Model model) {
      model.addAttribute("tovar", DBManager_L1t1.getItem(id));
      return "details_L1t2";
    }
}
