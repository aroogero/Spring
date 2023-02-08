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
public class HomeController { //вот и весь наш сервлет
    @GetMapping(value = "/")  //@WebServlet(value = "/") + doGet() запрос - Если пост запрос хотим отправить тогда PostMapping пишем
    public String index(Model model) {   //index - название метода (без разницы какая)  + в аргументах подтягиваем модел - спринговскую штуку
        ArrayList<Item> items = DBManager.getItems();
        model.addAttribute("tovary", items); //request.setAttribute("tovary", items); Но без Модел модел в аргументах индекс не работает
        //Если что-то собираемся отправить нужно обязательно подтягивать Модел

        //искусственно создаем 1 объект товара - для того чтобы вывести его детали
        Item best = new Item(777l,"Iphone 20", 1, 5000000);
        model.addAttribute("superTovar", best);

        return "indexpage";   //это одно и то же - что request.getDispatcher("/indexpage.html").forward(request,response);
    }
    //Например если хотим создать новую страницу создать, не надо новый Controller, Servlet создавать
//1 метод в контроллере это и есть ссылка. Как один целый сервлет. Он так и работает
    @GetMapping(value = "/about")
    public String about() { //пишем что возвращает String
        return "about";
    }
    @PostMapping(value="/add-item")
    public String addItem(@RequestParam(name="item_name") String name, //это то же, что и String name = request.getParameter("item_name");
    @RequestParam(name="item_price") double price,  //Не надо парсировать в дабл, так как RequestParam сам сделает так чтобы мы получили то что нам надо
    @RequestParam(name="item_amount") int amount){   //Эти параметры, они же аргументы метода, Значит они мне доступны
        Item item = new Item();
        item.setName(name);
        item.setPrice(price);
        item.setAmount(amount);
        DBManager.addItem(item);
        return "redirect:/";   //response.sendRedirect("/");
    }
}
