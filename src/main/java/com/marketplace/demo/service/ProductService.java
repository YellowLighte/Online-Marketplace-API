package com.marketplace.demo.service;

import com.marketplace.demo.exception.InformationNotFoundException;
import com.marketplace.demo.exception.RequiresHigherPermissionException;
import com.marketplace.demo.model.Category;
import com.marketplace.demo.model.Product;
import com.marketplace.demo.repository.CategoryRepository;
import com.marketplace.demo.repository.ProductRepository;
import com.marketplace.demo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
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

    public Product createProduct(Long categoryId, Product productObject) {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Category> category = categoryRepository.findById(categoryId);

        if (myUserDetails.getUser().isAdmin()) {
            if (!category.isPresent()) {
                throw new InformationNotFoundException("Category with the id " + categoryId + " was not found.");
            } else {
                productObject.setCategory(category.get());
                return productRepository.save(productObject);
            }
        } else {
            throw new RequiresHigherPermissionException("Must be logged into an account with a higher permission level.");
        }
    }

    public String deleteProduct(Long productId) {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Product> product = productRepository.findById(productId);

        if (myUserDetails.getUser().isAdmin()) {
            if (!product.isPresent()) {
                throw new InformationNotFoundException("Product with id " + productId + " was not found.");
            } else {
                productRepository.deleteById(productId);
                return "Product deleted successfully.";
            }
        } else {
            throw new RequiresHigherPermissionException("Must be logged into an account with a higher permission level.");
        }
    }

    public List<Product> getProductsByCategoryId(Long categoryId) {
        return productRepository.findByCategory_CategoryID(categoryId);
    }

//    public List<Product> getProductsByOrderId(Long orderId) {
//        return productRepository.f
//    }


}
