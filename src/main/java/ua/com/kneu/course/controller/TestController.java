package ua.com.kneu.course.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.com.kneu.course.entity.Categories;
import ua.com.kneu.course.service.CategoryService;

import java.util.List;

@Controller
public class TestController {

    private final CategoryService categoryService;

    public TestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping({"/", "/categories"})
    public String getHomePage(Model model,
                              HttpServletRequest request
                              ) {


        List<Categories> categoriesList = categoryService.findAll();

        HttpSession session = request.getSession();

        Object list1 = session.getAttribute("list1");

        model.addAttribute("list1", (list1 == null) ? "false" : "true");
        model.addAttribute("hello", "Hello World!");
        model.addAttribute("categories", categoryService.findAll());

        return "index1";
    }

    @GetMapping("/delivery")
    public String getDeliveryPage(Model model) {

        model.addAttribute("hello", "Hello World!");
        model.addAttribute("categories", categoryService.findAll());

        return "delivery";
    }

    @GetMapping("/payment")
    public String getPaymentPage(Model model) {

        model.addAttribute("hello", "Hello World!");
        model.addAttribute("categories", categoryService.findAll());

        return "payment";
    }

    @GetMapping("/about-us")
    public String getInfoPage(Model model) {

        model.addAttribute("hello", "Hello World!");
        model.addAttribute("categories", categoryService.findAll());

        return "about-us";
    }



}
