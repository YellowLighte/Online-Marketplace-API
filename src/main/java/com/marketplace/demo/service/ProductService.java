package com.marketplace.demo.service;

import com.marketplace.demo.exception.InformationNotFoundException;
import com.marketplace.demo.model.Product;
import com.marketplace.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(Long productId) {
        Optional product = productRepository.findById(productId);
        if (product.isPresent()) {
            return product;
        } else {
            throw new InformationNotFoundException("product with id " + productId + " not found.");
        }
    }

    public Product createProduct(Product productObject) {
        return productRepository.save(productObject);
    }
}
