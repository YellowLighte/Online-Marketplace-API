package com.marketplace.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;

    @Column
    private String name;

    @Column
    private String imagePath;

    @Column
    private double price;

    @Column
    private String color;

    public Product(Long productID, String name, String imagePath, double price, String color) {
        this.productID = productID;
        this.name = name;
        this.imagePath = imagePath;
        this.price = price;
        this.color = color;
    }

    public Product() {
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", name='" + name + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                '}';
    }
}
