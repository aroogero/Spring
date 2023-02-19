package kz.bootcamp4.springboot.bootcamp4.springboot.controllers;

import kz.bootcamp4.springboot.bootcamp4.springboot.db.DBManager_L3t1;
import kz.bootcamp4.springboot.bootcamp4.springboot.db.Students_L3t1;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class HomeController_L3t1 {
    @GetMapping(value="/homePage_L3t1")
    public String homepage_L3t3 (Model model) {
        ArrayList <Students_L3t1> students = DBManager_L3t1.getStudents();
        model.addAttribute("spisokStudentov", students);
        return "homePage_L3t1";
    }
    @GetMapping(value="/addStudentOpen")
    public String addOpen (){
        return "addStudent_L3t1";
    }
    @PostMapping(value="/addStudent_L3t1")
    public String addStudent_L3t (Students_L3t1 newStudent) {
        DBManager_L3t1.addStudents(newStudent);
        return "redirect:/homePage_L3t1";
    }
}