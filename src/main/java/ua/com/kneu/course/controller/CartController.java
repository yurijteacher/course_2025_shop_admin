package ua.com.kneu.course.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.kneu.course.bl.Cart;
import ua.com.kneu.course.entity.Products;
import ua.com.kneu.course.service.CategoryService;

@Controller
public class CartController {

    private final CategoryService categoryService;

    public CartController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/cart")
    public String getPageCart(Model model,
                              HttpServletRequest request
                              ) {

        HttpSession session = request.getSession();

        Cart cart = (Cart) session.getAttribute("cart");

        if(cart == null) {
            cart = new Cart();
        }
        model.addAttribute("cart", cart.getCart());
        model.addAttribute("totalValue", cart.getTotalValue());
        model.addAttribute("sumEl", cart.getSumElFromCart());

        model.addAttribute("categories", categoryService.findAll());


        return "cart";
    }



    @PostMapping("/addToCart")
    public String addToCart(@RequestParam(name = "id") Products product,
                            @RequestParam(name = "quantity") int quantity,
                            HttpServletRequest request
                            ){

        HttpSession session = request.getSession();

        Cart cart = (Cart) session.getAttribute("cart");

        if(cart == null) {
            cart = new Cart();
        }

        cart.addNewItemToCart(product, quantity);

        session.setAttribute("cart", cart);


        // session.setAttribute("ojb", obj);
        // session.getAttribute("obj");

        return "redirect:/cart";
    }

    @PostMapping("/updateItemFromCart")
    public String updateItemFromCart(@RequestParam(name = "id") Products product,
                                     @RequestParam(name = "quantity") int quantity,
                                     HttpServletRequest request
    ) {

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) cart = new Cart();

        cart.updateItemFromCart(product, quantity);
        session.setAttribute("cart", cart);

        return "redirect:/cart";
    }


    @PostMapping("/deleteItemFromCart")
    public String deleteItemFromCart(@RequestParam(name = "id") Products product,
                                     HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) cart = new Cart();

        cart.deleteItemFromCart(product);
        session.setAttribute("cart", cart);

        return "redirect:/cart";
    }


    @PostMapping("/deleteAllItemFromCart")
    public String deleteAllFromCart(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) cart = new Cart();
        cart.deleteAllItemsFromCart();

        session.setAttribute("cart", cart);

        return "redirect:/cart";
    }





}
