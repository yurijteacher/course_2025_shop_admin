package ua.com.kneu.course.controller;


import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.kneu.course.entity.Clients;
import ua.com.kneu.course.entity.Roles;
import ua.com.kneu.course.repository.ClientsRepository;
import ua.com.kneu.course.service.ClientService;
import ua.com.kneu.course.service.CustomerService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class ClientAdminController {

    private final ClientService clientService;
    private final CustomerService customerService;
    private  final ClientsRepository clientsRepository;


    public ClientAdminController(ClientService clientService, CustomerService customerService, ClientsRepository clientsRepository) {
        this.clientService = clientService;
        this.customerService = customerService;
        this.clientsRepository = clientsRepository;
    }


    @GetMapping("/admin/list-client")
    public String getClientList(Model model) {

        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("customers", customerService.getAllCustomers());

        return "list-clients-admin";
    }

    @GetMapping("/admin/update-client")
    public String getPageUpdateClient(Model model) {

        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("customers", customerService.getAllCustomers());

        return "update_username_password";
    }


    @PostMapping("/updateUsernameAndPassword")
    public String updateUsernameAndPassword(@RequestParam(name = "username") String username,
                                            @RequestParam(name = "password") String password,
                                            @RequestParam(name = "id") Clients client) {

        Clients client1 = clientService.getClientByUsername(username);

//        if (client1 == null) {
            client.setUsername(username);

            client.setPassword(new BCryptPasswordEncoder().encode(password));
            clientService.saveNewClient(client);
//        } else {
            // ...
//        }

        return "redirect:/admin/list-client";
    }

    @GetMapping("/admin/roles-has-clients")
    public String getRolesHasClients(Model model) {

        model.addAttribute("clients", clientService.getAllClients());

        return "roles-has-clients";
    }

    @PostMapping("/saveNewRoleForClient")
    public String saveNewRoleForClient(@RequestParam("id") Long clientId,
                                       @RequestParam("id") Clients client,
                                       @RequestParam("roles") Long roleId
                                       )
    {
        // перевірка на додані ролі !

        Set<Roles> roles = clientService.getClientByUsername(client.getUsername()).getRolesset();

        boolean logic = false;

        for (Roles role : roles) {
            if (role.getId()==roleId) {
                logic = true;
            }
        }

        if (!logic) clientsRepository.saveNewRoleForClient(clientId, roleId);

         return "redirect:/admin/list-client";
    }

}
