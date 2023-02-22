package kz.bootcamp4.springboot.bootcamp4.springboot.controllers;

import kz.bootcamp4.springboot.bootcamp4.springboot.beans.FirstBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OtherController {
    @Autowired
    private FirstBean firstBean;
    @GetMapping(value = "/indexPage")
    public String index(Model model) {
        System.out.println(firstBean.getData());
        return "indexPage";
    }
    @GetMapping(value = "changeValue")
    public String change() {
        firstBean.setName("Nurzhan");
        firstBean.setValue(888);
        return "redirect:/";
    }
}
