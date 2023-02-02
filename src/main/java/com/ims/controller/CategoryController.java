package com.ims.controller;

import com.ims.entity.Category;
import com.ims.service.CategorySerivce;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategorySerivce categorySerivce;

    public CategoryController(CategorySerivce categorySerivce) {
        this.categorySerivce = categorySerivce;
    }
    @PostMapping
    public Category addCategory (@RequestBody Category category){return categorySerivce.addCategory(category);}
    @PutMapping
    public Category updateCategory (@RequestBody Category category){return categorySerivce.updateCategory(category);}
    @DeleteMapping("/{id}")
    public void deleteCategory (@PathVariable int id){ categorySerivce.deleteCategory(id);}
    public List<Category> getCategories(){return categorySerivce.getCategories();}
    @GetMapping("/{id}")
    public  Category getCategoryById(@PathVariable int id){return categorySerivce.getCategoryById(id);}


}
