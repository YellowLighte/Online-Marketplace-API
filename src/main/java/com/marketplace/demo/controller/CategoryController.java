package com.marketplace.demo.controller;

import com.marketplace.demo.model.Category;
import com.marketplace.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // http://localhost:9092/api/hello
    @GetMapping(path = "/hello")
    public String helloWorld() {
        return "Hello world";
    }

    // http://localhost:9092/api/categories
    @GetMapping(path = "/categories")
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    // http://localhost:9092/api/categories/{categoryId}
    @GetMapping(path = "/categories/{categoryId}")
    public Optional getCategory(@PathVariable Long categoryId) {
        return categoryService.getCategory(categoryId);
    }

    // http://localhost:9092/api/categories
    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category categoryObject) {
        return categoryService.createCategory(categoryObject);
    }

    // http://localhost:9092/api/categories/{categoryId}
    @DeleteMapping("/categories/{categoryId}")
    public String deleteCategory(@PathVariable Long categoryId) {
        return categoryService.deleteCategory(categoryId);
    }
}
