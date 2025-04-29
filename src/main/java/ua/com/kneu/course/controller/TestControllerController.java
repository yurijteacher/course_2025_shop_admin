package ua.com.kneu.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestControllerController {

//    @GetMapping("/test")
//    public String getTestPage(Model model){
//        return "index";
//    }

    @GetMapping("/admin")
    public String getAdminPage(Model model){
        return "admin_page";
    }

    @GetMapping("/manager")
    public String getManagerPage(Model model){
        return "manager_page";
    }


}
