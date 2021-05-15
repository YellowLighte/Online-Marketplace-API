package com.marketplace.demo.controller;

import com.marketplace.demo.exception.InformationNotFoundException;
import com.marketplace.demo.model.Product;
import com.marketplace.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{productId}")
    public Optional<Product> getProduct(@PathVariable Long productId) {
        Optional product = productRepository.findById(productId);
        if (product.isPresent()) {
            return product;
        } else {
            throw new InformationNotFoundException("product with id " + productId + " not found.");
        }
    }
}
