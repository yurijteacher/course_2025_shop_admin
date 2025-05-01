package ua.com.kneu.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.com.kneu.course.entity.Categories;
import ua.com.kneu.course.service.CategoryService;
import ua.com.kneu.course.service.SaveCategoryToDBFromExcel;
import ua.com.kneu.course.validation.Valid;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoryManagerController {

    private final CategoryService categoryService;
    private final SaveCategoryToDBFromExcel saveCategoryToDBFromExcel;


    public CategoryManagerController(CategoryService categoryService, SaveCategoryToDBFromExcel saveCategoryToDBFromExcel) {
        this.categoryService = categoryService;
        this.saveCategoryToDBFromExcel = saveCategoryToDBFromExcel;
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



    @PostMapping("saveFromExcel")
    public String saveCategoryToDbFromExcel(@RequestParam("file") MultipartFile file) {

        List<Categories> categories = new ArrayList<>();
        if (file != null && !file.getOriginalFilename().isEmpty()) {

            Valid valid = new Valid();
            try (InputStream is = file.getInputStream()) {
                if (valid.logicXLS(file.getOriginalFilename())) {
                    categories = saveCategoryToDBFromExcel.saveListCategoryToDbFromExcel(is);
                    categoryService.saveCategories(categories);
                } else if (valid.logicXLSX(file.getOriginalFilename())) {
                    categories = saveCategoryToDBFromExcel.saveListCategoryToDbFromExcel2(is);
                    categoryService.saveCategories(categories);
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Обробка винятків
            }
        }
        return "redirect:/manager/categories";
    }

}
