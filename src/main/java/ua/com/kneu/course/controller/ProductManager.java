package ua.com.kneu.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.kneu.course.entity.Categories;
import ua.com.kneu.course.entity.Products;
import ua.com.kneu.course.service.CategoryService;
import ua.com.kneu.course.service.ProductService;

import java.math.BigDecimal;

@Controller
public class ProductManager {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductManager(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/manager/products")
    public String getProductsManagerPages(Model model) {

        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("categories", categoryService.findAll());

        return "products_manager";
    }

    @PostMapping("/saveNewProduct")
    public String saveNewProduct(@RequestParam(name = "name") String name,
                                 @RequestParam(name = "short_description") String short_description,
                                 @RequestParam(name = "full_description") String full_description,
                                 @RequestParam(name = "price") double price,
                                 @RequestParam(name = "categories") Categories category
                                 ) {

        Products product = new Products(name, short_description, full_description, new BigDecimal(price), category);

        productService.saveProduct(product);

        return "redirect:/manager/products";
    }

}
