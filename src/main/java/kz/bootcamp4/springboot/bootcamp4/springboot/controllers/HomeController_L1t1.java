package kz.bootcamp4.springboot.bootcamp4.springboot.controllers;

import kz.bootcamp4.springboot.bootcamp4.springboot.db.DBManager_L1t1;
import kz.bootcamp4.springboot.bootcamp4.springboot.db.Item_L1t1;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
@Controller
public class HomeController_L1t1 {
    @GetMapping(value = "/homePage_L1T1")
    public String methodName(Model model) {
        ArrayList<Item_L1t1> items = DBManager_L1t1.getItems();
        model.addAttribute("tovary1", items);
        return "homePage_L1t1";
    }
}
