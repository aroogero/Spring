package kz.bootcamp4.springboot.bootcamp4.springboot.controllers;

import kz.bootcamp4.springboot.bootcamp4.springboot.db.Courses_L6t1;
import kz.bootcamp4.springboot.bootcamp4.springboot.db.DBManager_L6t1;
import kz.bootcamp4.springboot.bootcamp4.springboot.model.Application_L6t1;
import kz.bootcamp4.springboot.bootcamp4.springboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController_L6t1 {

    @Autowired
    private StudentRepository studentRepository;

  /* @GetMapping(value = "/homePage_L6t1")
    public String homePage(Model model) {
        List<Application_L6t1> apples = studentRepository.findAll();
        model.addAttribute("applications", apples);
        return "homePage_L6t1";
    }

   /* @GetMapping (value="/addApple")
    public String addApple(){
        return "addApplication_L6t1";
    }*/

   /* @GetMapping(value="/addApple_L6t1")
    public String addApplication(Model model){
        ArrayList<Courses_L6t1> course = DBManager_L6t1.getCourses();
        model.addAttribute("courses", course);
        return "addApplication_L6t1";
    }

    @PostMapping(value = "/addApplication_L6t1")
    public String addApplicationByObject(Application_L6t1 student) {
        student.setLink(student.getFullName().toLowerCase().replace(' ','-'));
        studentRepository.save(student);
        return "redirect:/addApple_L6t1?success";
    }

    @GetMapping(value = "/details/{id}/{link}.html")
    public String detailsApple(@PathVariable(name = "id") Long id,
                               @PathVariable(name = "link") String link,
                               Model model){
        Application_L6t1 application = studentRepository.findById(id).get();
        model.addAttribute("zaiavka", application);
        return "details_L6t1";
    }
    */

}
