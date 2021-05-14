package com.marketplace.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class CategoryController {

    // http://localhost:9092/api/hello
    @GetMapping(path = "/hello")
    public String helloWorld() {
        return "Hello world";
    }

    // http://localhost:9092/api/categories
    @GetMapping(path = "/categories")
    public String getCategories() {
        System.out.println("calling getCategories() from CategoryController");
        return "CategoryController is getting all the categories";
    }

    // http://localhost:9092/api/categories/{categoryId}
    @GetMapping(path = "/categories/{categoryId}")
    public String getCategory() {
        System.out.println("calling getCategory() from CategoryController");
        return "CategoryController is getting a single category";
    }
}
