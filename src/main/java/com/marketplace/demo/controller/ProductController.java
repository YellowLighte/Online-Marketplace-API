package com.marketplace.demo.controller;

import com.marketplace.demo.exception.InformationNotFoundException;
import com.marketplace.demo.model.Product;
import com.marketplace.demo.repository.ProductRepository;
import com.marketplace.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/products/{productId}")
    public Optional<Product> getProduct(@PathVariable Long productId) {
        return productService.getProduct(productId);
    }

    // http://localhost:9092/api/{categoryId}/products
    @PostMapping("/{categoryId}/products")
    public Product createProduct(@PathVariable Long categoryId, @RequestBody Product productObject) {
        return productService.createProduct(categoryId, productObject);
    }


}
