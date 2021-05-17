package com.marketplace.demo.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryID;

    @Column
    private String name;

    @Column
    private String imagePath;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    public Category(Long categoryID, String name, String imagePath) {
        this.categoryID = categoryID;
        this.name = name;
        this.imagePath = imagePath;
    }

    public Category() {
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryID=" + categoryID +
                ", name='" + name + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", products=" + products +
                '}';
    }
}
