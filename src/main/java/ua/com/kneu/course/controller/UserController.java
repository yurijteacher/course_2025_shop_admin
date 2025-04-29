package ua.com.kneu.course.controller;

import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.com.kneu.course.entity.Clients;
import ua.com.kneu.course.entity.Customers;
import ua.com.kneu.course.entity.Roles;
import ua.com.kneu.course.service.CategoryService;
import ua.com.kneu.course.service.ClientService;
import ua.com.kneu.course.service.CustomerService;

import java.util.Collections;

@Controller
public class UserController {

    private final ClientService clientService;
    private final CustomerService customerService;
    private final CategoryService categoryService;


    public UserController(ClientService clientService, CustomerService customerService, CategoryService categoryService) {
        this.clientService = clientService;
        this.customerService = customerService;
        this.categoryService = categoryService;
    }

    @GetMapping("/login")
    public String getPageLogin(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "login";
    }

    @GetMapping("/registration")
    public String getPageRegistration(Model model) {

        model.addAttribute("customers", new Customers());
        model.addAttribute("clients", new Clients());
        model.addAttribute("categories", categoryService.findAll());

        return "registration";
    }

    @PostMapping("/registration")
    public String registrationNewUser(@Valid Customers customer,
                                      BindingResult bindingResult1,
                                      @Valid Clients client,
                                      BindingResult bindingResult2
    ){

        if (bindingResult1.hasErrors()) {
            return "registration";
        }

        if (bindingResult2.hasErrors()) {
            return "registration";
        }

        // Перевірка чи існує login
        if(clientService.getClientByLogin(client.getUsername())){
            return "redirect:/registration";
        }


        String password = new BCryptPasswordEncoder().encode(client.getPassword());
        client.setPassword(password);

        Clients clients = clientService.saveNewClient(client);

        clients.setRolesset(Collections.singleton(new Roles(1L, "ROLE_USER")));

        customer.setClient(clients);

        customerService.saveNewCustomer(customer);

        return "redirect:/login";
    }


}
