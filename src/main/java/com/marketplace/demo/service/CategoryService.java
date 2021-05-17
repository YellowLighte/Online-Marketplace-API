package com.marketplace.demo.service;

import com.marketplace.demo.exception.InformationExistException;
import com.marketplace.demo.exception.InformationNotFoundException;
import com.marketplace.demo.exception.RequiresHigherPermissionException;
import com.marketplace.demo.model.Category;
import com.marketplace.demo.repository.CategoryRepository;
import com.marketplace.demo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Optional getCategory(Long categoryId) {
        Optional category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            return category;
        } else {
            throw new InformationNotFoundException("category with id " + categoryId + " not found.");
        }
    }

    public Category createCategory(Category categoryObject) {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Category category = categoryRepository.findByName(categoryObject.getName());

        if (myUserDetails.getUser().isAdmin()) {
            if (category != null) {
                throw new InformationExistException("A category with the name " + categoryObject.getName() + " already exists.");

            } else {
                return categoryRepository.save(categoryObject);
            }
        } else {
            throw new RequiresHigherPermissionException("Must be logged into an account with a higher permission level.");
        }
    }

    public String deleteCategory(Long categoryId) {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Category> category = categoryRepository.findById(categoryId);

        if (myUserDetails.getUser().isAdmin()) {
            if (!category.isPresent()) {
                throw new InformationNotFoundException("Category with id " + categoryId + " not found.");
            } else {
                categoryRepository.deleteById(categoryId);
                return "Category successfully deleted.";
            }
        } else {
            throw new RequiresHigherPermissionException("Must be logged into an account with a higher permission level.");
        }
    }
}
