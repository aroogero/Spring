package kz.bootcamp4.springboot.bootcamp4.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController { //вот и весь наш сервлет
    @GetMapping(value = "/")  //@WebServlet(value = "/") + doGet() запрос - Если пост запрос хотим отправить тогда PostMapping пишем
    public String index() {   //index - название метода (без разницы какая)
        return "indexpage";   //это одно и то же - что request.getDispatcher("/indexpage.html").forward(request,response);
    }
    //Например если хотим создать новую страницу создать, не надо новый Controller, Servlet создавать
//1 метод в контроллере это и есть ссылка. Как один целый сервлет. Он так и работает
    @GetMapping(value = "/about")
    public String about() {
        return "about";
    }
}
