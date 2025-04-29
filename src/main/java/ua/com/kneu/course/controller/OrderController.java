package ua.com.kneu.course.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.kneu.course.bl.Cart;
import ua.com.kneu.course.bl.ItemCart;
import ua.com.kneu.course.entity.*;
import ua.com.kneu.course.service.*;

import java.util.Date;

@Controller
public class OrderController {

    private final ClientService clientService;
    private final OrderService orderService;
    private final CustomerService customerService;
    private final CategoryService categoryService;
    private final ProductHasOrderService productHasOrderService;


    public OrderController(ClientService clientService, OrderService orderService, CustomerService customerService, CategoryService categoryService, ProductHasOrderService productHasOrderService) {
        this.clientService = clientService;
        this.orderService = orderService;
        this.customerService = customerService;
        this.categoryService = categoryService;
        this.productHasOrderService = productHasOrderService;
    }


    @GetMapping("/order")
    public String getPageOrder(Model model,
                               HttpServletRequest request){

        HttpSession session = request.getSession();

//        Object user = session.getAttribute("user");
//
//        if(user == null){
//            return "redirect:/login";
//        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Clients client = (Clients) clientService.loadUserByUsername(authentication.getName());
        Long clientId = client.getId();

        if(clientId == null){
            return "redirect:/login";
        }

        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            return "redirect:/";
        }

        model.addAttribute("customer", customerService.getCustomerById(clientId));
        model.addAttribute("cart", cart.getCart());
        model.addAttribute("values", cart.getTotalValue());
        model.addAttribute("el", cart.getSumElFromCart());
        model.addAttribute("categories", categoryService.findAll());

        return "order";
    }

    @PostMapping("/order")
    public String saveNewOrderToDB(
            @RequestParam(name = "delivery") Delivery delivery,
            @RequestParam(name = "payment") Payment payment,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes
            ){

        HttpSession session = request.getSession();

       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       Clients client = (Clients) clientService.loadUserByUsername(authentication.getName());
       Long clientId = client.getId();

        if(clientId == null){
            return "redirect:/login";
        }

        Cart cart = (Cart) session.getAttribute("cart");

        if(cart == null){
            return "redirect:/";
        }

        Orders order = new Orders();
        order.setDelivery(delivery);
        order.setPayment(payment);
        order.setDateCreated(new Date());
        Customers customer = customerService.getCustomerById(clientId);
        order.setCustomer(customer);
        order.setStatus("Необроблене замовлення");

        Orders orderId =  orderService.saveNewOrder(order);

        for(ItemCart el : cart.getCart()){
            productHasOrderService.saveNewProductByOrder(el.getProduct(), orderId, el.getQuantity());
        }

        cart.deleteAllItemsFromCart();

        session.setAttribute("cart", cart);

        redirectAttributes.addAttribute("orderId", orderId);

        return "redirect:/thank";
    }

    @GetMapping("/thank")
    public String getThankPage(@RequestParam(name = "orderId", defaultValue = " ") Long orderId,
                               Model model){

        model.addAttribute("orderId", orderId);

        return "thank";
    }


}
