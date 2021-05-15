package com.marketplace.demo.controller;

import com.marketplace.demo.exception.InformationNotFoundException;
import com.marketplace.demo.model.Category;
import com.marketplace.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class CategoryController {

    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // http://localhost:9092/api/hello
    @GetMapping(path = "/hello")
    public String helloWorld() {
        return "Hello world";
    }

    // http://localhost:9092/api/categories
    @GetMapping(path = "/categories")
    public List<Category> getCategories() {
        System.out.println("calling getCategories() from CategoryController");
        return categoryRepository.findAll();
    }

    // http://localhost:9092/api/categories/{categoryId}
    @GetMapping(path = "/categories/{categoryId}")
    public Optional getCategory(@PathVariable Long categoryId) {
        System.out.println("calling getCategory() from CategoryController");
        Optional category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            return category;
        } else {
            throw new InformationNotFoundException("category with id " + categoryId + " not found.");
        }
    }
}
