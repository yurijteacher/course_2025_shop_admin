package ua.com.kneu.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.kneu.course.entity.Categories;
import ua.com.kneu.course.service.CategoryService;

@Controller
public class CategoryManagerController {

    private final CategoryService categoryService;


    public CategoryManagerController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/manager/categories")
    public String getCategoriesPages(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "category_manager";
    }


    @PostMapping("/saveNewCategory")
    public String saveNewCategory(@RequestParam(name = "name") String name,
                                  @RequestParam(name = "description") String description,
                                  @RequestParam(name = "image") String images
                                  ) {

        Categories category = new Categories();
        category.setName(name);
        category.setDescription(description);
        category.setLinkImages(images);

        categoryService.save(category);

        return "redirect:/manager/categories";

    }

    @PostMapping("/deleteCategoryFromList")
    public String deleteCategoryFromList(@RequestParam(name = "id1") Categories category) {

        // validation
        categoryService.deleteById(category.getId());

        return "redirect:/manager/categories";
    }

    @PostMapping("/updateCategory")
    public String updateCategory(@RequestParam(name = "name") String name,
                                 @RequestParam(name = "description") String description,
                                 @RequestParam(name = "image") String images,
                                 @RequestParam(name = "id1") Categories category
                                 ){

        category.setName(name);
        category.setDescription(description);
        category.setLinkImages(images);

        categoryService.update(category);

        return "redirect:/manager/categories";
    }

    @PostMapping("/deleteAllCategory")
    public String deleteAllCategory() {
        categoryService.deleteAll();
        return "redirect:/manager/categories";
    }

}
